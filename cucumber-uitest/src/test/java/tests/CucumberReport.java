package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class CucumberReport {

	@Test
	public void test() {
		System.out.println("report *****************");
		report();
	}

	public void report() {
		try {
			File reportOutputDirectory = new File("target");
			List<String> jsonFiles = new ArrayList<String>();
			jsonFiles.add("target/cucumber.json");
			jsonFiles.add("target/iframe/cucumber.json");
			jsonFiles.add("target/wapaqcenter/cucumber.json");
			jsonFiles.add("target/yeyou/cucumber.json");
			jsonFiles.add("target/payweb/cucumber.json");
			jsonFiles.add("target/paywap/cucumber.json");
			String jenkinsBasePath = "";
			String buildNumber = "1";
			String projectName = "cucumber-jvm";
			boolean skippedFails = true;
			boolean pendingFails = false;
			boolean undefinedFails = true;
			boolean missingFails = true;
			boolean runWithJenkins = false;
			boolean parallelTesting = false;

			Configuration configuration = new Configuration(reportOutputDirectory, projectName);
			// optionally only if you need
			configuration.setStatusFlags(skippedFails, pendingFails, undefinedFails, missingFails);
			configuration.setParallelTesting(parallelTesting);
			configuration.setJenkinsBasePath(jenkinsBasePath);
			configuration.setRunWithJenkins(runWithJenkins);
			configuration.setBuildNumber(buildNumber);

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
