import model.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static public void main(String[] args) {
        StringBuilder outputBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("###")) {
            Model model = new Model();
            while (!input.equals("#")) {
                String parentName;
                FeatureType featureType = FeatureType.SIMPLE; // default, when there's only one child
                String[] childrenNames = null;

                if (input.contains("=")) {
                    String[] equality = input.replace(" ", "").split("=");

                    parentName = equality[0];

                    for (String delimiter : new String[] {"+", "|", "^"})
                        if (equality[1].contains(delimiter))
                            featureType = FeatureType.typeOf(delimiter);

                    childrenNames = equality[1].split(featureType.getDelimiterAsRegex());
                } else
                    parentName = input;

                model.addFeature(parentName, featureType, childrenNames);
                input = scanner.nextLine();
            }
            input = scanner.nextLine();
            while(!input.equals("##")) {
                outputBuilder.append(
                        model.validate(parseWithComma(input)) ?
                                "Valid\n" :
                                "Invalid\n"
                );
                input = scanner.nextLine();
            }
            outputBuilder.append("+++\n");
            input = scanner.nextLine();
        }
        System.out.println(outputBuilder);
    }

    private static List<String> parseWithComma(String testCase) {
        String[] names = testCase.replaceAll("[{}\\s]", "").split(",");
        return new LinkedList<>(Arrays.asList(names));
    }
}
