/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.batch.curator.nlp;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.LabeledWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.LabeledScoredConstituentFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Vishal Reddy
 */
public class NLPUtil {

	public static Set<String> getKeywords(String text) {
		return _instance._getKeywords(text);
	}

	public static boolean isEnglish(String text) {
		return _instance._isEnglish(text);
	}

	private static String _buildKeyword(List<LabeledWord> labeledWords) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < labeledWords.size(); i++) {
			LabeledWord labeledWord = labeledWords.get(i);

			Label label = labeledWord.tag();

			String labelString = label.value();

			String word = labeledWord.word();

			if ((i == 0) || (labelString.equals("POS") && word.equals("'s")) ||
				labelString.equals("-RRB-") ||
				(labelString.equals(":") && word.equals(":"))) {
			}
			else {
				LabeledWord previousLabeledWord = labeledWords.get(i - 1);

				Label previousLabel = previousLabeledWord.tag();

				String previousLabelString = previousLabel.value();

				if (previousLabelString.equals("-LRB-")) {
				}
				else {
					sb.append(" ");
				}
			}

			if (labelString.equals("-LRB-")) {
				sb.append("(");
			}
			else if (labelString.equals("-RRB-")) {
				sb.append(")");
			}
			else {
				sb.append(word);
			}
		}

		return sb.toString();
	}

	private static boolean _isSkipConstituentLabeledWords(
		List<LabeledWord> labeledWords) {

		for (LabeledWord labeledWord : labeledWords) {
			Label label = labeledWord.tag();

			String labelString = label.value();

			if (labelString.equals(",") || labelString.equals(":") ||
				labelString.equals("IN") || labelString.equals("PRP") ||
				labelString.equals("VB") || labelString.equals("VBD") ||
				labelString.equals("VBG") || labelString.equals("VBN") ||
				labelString.equals("VBP") || labelString.equals("VBZ")) {

				if (_log.isDebugEnabled()) {
					_log.debug("Removing: " + labelString);
				}

				return true;
			}
		}

		return false;
	}

	private static void _removeConstituentLabeledWords(
		List<LabeledWord> labeledWords) {

		LabeledWord labeledWord = labeledWords.get(0);

		Label label = labeledWord.tag();

		String labelString = label.value();

		while (labelString.equals("CD") || labelString.equals("DT") ||
			   labelString.equals("PRP$")) {

			labeledWords.remove(0);

			if (labeledWords.isEmpty()) {
				break;
			}

			labeledWord = labeledWords.get(0);

			label = labeledWord.tag();

			labelString = label.value();

			if (_log.isDebugEnabled()) {
				_log.debug("Removing: " + labelString);
			}
		}
	}

	private static List<LabeledWord> _removeParentheticals(
		List<LabeledWord> labeledWords) {

		int leftParenthesisIndex = -1;
		int rightParenthesisIndex = -1;

		for (int i = labeledWords.size() - 1; i > 0; i--) {
			LabeledWord labeledWord = labeledWords.get(i);

			Label label = labeledWord.tag();

			String labelString = label.value();

			if (labelString.equals("-RRB-")) {
				rightParenthesisIndex = i;
			}
			else if (labelString.equals("-LRB-")) {
				leftParenthesisIndex = i;
			}

			if ((leftParenthesisIndex != -1) && (rightParenthesisIndex != -1) &&
				(leftParenthesisIndex < rightParenthesisIndex)) {

				List<LabeledWord> beforeLeftParenthesisLabeledWords =
					labeledWords.subList(0, leftParenthesisIndex);

				List<LabeledWord> beforeRightParenthesisLabeledWords =
					labeledWords.subList(
						rightParenthesisIndex + 1, labeledWords.size());

				beforeLeftParenthesisLabeledWords.addAll(
					beforeRightParenthesisLabeledWords);

				labeledWords = beforeLeftParenthesisLabeledWords;

				leftParenthesisIndex = -1;
				rightParenthesisIndex = -1;
			}
		}

		return labeledWords;
	}

	private NLPUtil() {
		Properties properties = new Properties();

		properties.setProperty("annotators", "tokenize,ssplit,pos,parse");
		properties.setProperty("parse.maxlen", "100");

		_stanfordCoreNLP = new StanfordCoreNLP(properties);

		try {
			DetectorFactory.loadProfile(
				ResourceUtil.readResourcesToList("profiles/*"));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private List<String> _buildKeywords(String text) {
		List<String> keywords = new ArrayList<>();

		Annotation annotation = new Annotation(text);

		_stanfordCoreNLP.annotate(annotation);

		List<CoreMap> coreMaps = annotation.get(
			CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap coreMap : coreMaps) {
			Tree tree = coreMap.get(TreeCoreAnnotations.TreeAnnotation.class);

			Set<Constituent> constituents = tree.constituents(
				new LabeledScoredConstituentFactory());

			for (Constituent constituent : constituents) {
				Label label = constituent.label();

				if (label == null) {
					continue;
				}

				String labelString = label.toString();

				if (!labelString.equals("NP")) {
					continue;
				}

				List<LabeledWord> labeledWords = tree.labeledYield();

				List<LabeledWord> constituentLabeledWords =
					labeledWords.subList(
						constituent.start(), constituent.end() + 1);

				if (_isSkipConstituentLabeledWords(constituentLabeledWords)) {
					continue;
				}

				_removeConstituentLabeledWords(constituentLabeledWords);

				if (constituentLabeledWords.isEmpty()) {
					continue;
				}

				constituentLabeledWords = _removeParentheticals(
					constituentLabeledWords);

				if (constituentLabeledWords.isEmpty()) {
					continue;
				}

				LabeledWord lastConstituentLabeledWord =
					constituentLabeledWords.get(
						constituentLabeledWords.size() - 1);

				Label lastConstituentLabel = lastConstituentLabeledWord.tag();

				String lastConstituentLabelString =
					lastConstituentLabel.value();

				String lastWord = lastConstituentLabeledWord.word();

				if (lastConstituentLabelString.equals("POS") &&
					lastWord.equals("'s")) {

					if (_log.isDebugEnabled()) {
						_log.debug("Removing: " + lastConstituentLabeledWord);
					}

					constituentLabeledWords.remove(
						constituentLabeledWords.size() - 1);
				}

				String keyword = _buildKeyword(constituentLabeledWords);

				if (keywords.contains(keyword)) {
					continue;
				}

				keywords.add(keyword);
			}
		}

		return keywords;
	}

	private Set<String> _getKeywords(String text) {
		Set<String> keywords = new HashSet<>();

		String[] parts = text.split("(\\s+\\|\\s+)|(\\s+-\\s+)");

		for (String part : parts) {
			keywords.addAll(_buildKeywords(part));
		}

		return keywords;
	}

	private boolean _isEnglish(String text) {
		Charset charset = StandardCharsets.US_ASCII;

		CharsetEncoder charsetEncoder = charset.newEncoder();

		if (!charsetEncoder.canEncode(text)) {
			return false;
		}

		try {
			DetectorFactory.setSeed(0);

			Detector detector = DetectorFactory.create();

			detector.append(text);

			List<Language> languages = detector.getProbabilities();

			for (Language language : languages) {
				if (Objects.equals(language.lang, "en") &&
					(language.prob >= 0.5)) {

					return true;
				}
			}
		}
		catch (LangDetectException lde) {
			_log.error(lde);
		}

		return false;
	}

	private static final Log _log = LogFactory.getLog(NLPUtil.class);

	private static NLPUtil _instance = new NLPUtil();

	private final StanfordCoreNLP _stanfordCoreNLP;

}