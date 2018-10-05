package com.ziemo.requests.view;

import com.ziemo.requests.model.Counters;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.Map;

public class WebDisplay {

	public static String createRootPage() {

		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/root.twig");
		JtwigModel model = JtwigModel.newModel();

		return template.render(model);
	}

	public static String createCounterPage(String method) {

		String message = String.format("You have just increased method %s", method);

		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/counter.twig");
		JtwigModel model = JtwigModel.newModel();

		model.with("message", message);

		return template.render(model);
	}

	public static String createStatisticsPage() {

		Map<String, Integer> data = Counters.data;

		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/statistics.twig");
		JtwigModel model = JtwigModel.newModel();

		model.with("data", data);

		return template.render(model);
	}

	public static String createGeneratorPage(String jsonToData) {

		JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/generator.twig");
		JtwigModel model = JtwigModel.newModel();


		model.with("jsonInsight", jsonToData);

		return template.render(model);
	}

}
