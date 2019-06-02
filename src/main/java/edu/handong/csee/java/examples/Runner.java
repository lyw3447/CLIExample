package edu.handong.csee.java.examples;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Runner {
	
	String path;
	boolean fullPath;
	boolean verbose;
	boolean help;

	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);
	}

	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option p");
			
			// TODO show the number of files in the path
			
			File file = new File(path);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getName());
			System.out.println(file.listFiles().length);
			if(verbose) {
				
				// TODO list all files in the path
				 for(int i = 0; i < file.listFiles().length; i++) {
					 System.out.println(file.getAbsolutePath());
				 }
				
				System.out.println("Your program is terminated. (This message is shown because you turned on -f option!");
			}
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			fullPath = cmd.hasOption("f");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();
		
				options.addOption(Option.builder("p").longOpt("path")
						.desc("Set a path of a directory or a file to display")
						.hasArg()
						.argName("Path name to display")
						.required()
						.build());
		
		options.addOption(Option.builder("f").longOpt("fullpath")
						.desc("Set a path of a directory or a file to display")
						.argName("Full path name to display")
						.build());
		
		options.addOption(Option.builder("v").longOpt("verbose")
						.desc("Display detailed messages!")
						.argName("verbose option")
						.build());
				
		options.addOption(Option.builder("h").longOpt("help")
				        .desc("Help")
				        .build());
				
		return options;
	}
	
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}
