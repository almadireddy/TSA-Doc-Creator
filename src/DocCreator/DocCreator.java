package DocCreator;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;

class DocCreator {
  private String name;
  private String classNum;
  private String procedure;
  private String filename;

  private XWPFDocument doc = new XWPFDocument();

  private XWPFParagraph title = doc.createParagraph();
  private XWPFRun titleRun = title.createRun();

  private XWPFParagraph problem = doc.createParagraph();
  private XWPFRun problemRun  = problem.createRun();
  private XWPFRun problemText = problem.createRun();

  private XWPFParagraph procedureParagraph = doc.createParagraph();
  private XWPFRun procedureRun  = procedureParagraph.createRun();
  private XWPFRun procedureText = procedureParagraph.createRun();

  private XWPFParagraph result = doc.createParagraph();
  private XWPFRun resultRun  = result.createRun();
  private XWPFRun resultText = result.createRun();

  private XWPFParagraph materials = doc.createParagraph();
  private XWPFRun materialRun  = materials.createRun();
  private XWPFRun materialText = materials.createRun();

  private int fontSize = 14;
  private int bigFontSize = 16;
  private String font = "Source Sans Pro Light";

  DocCreator() {
    name = "";
    classNum = "";
    procedure = "";

    titleRun.setFontFamily(font);
    titleRun.setFontSize(bigFontSize);

    problemRun.setFontFamily(font);
    problemRun.setFontSize(bigFontSize);

    problemText.setFontFamily(font);
    problemText.setFontSize(fontSize);

    procedureRun.setFontFamily(font);
    procedureRun.setFontSize(bigFontSize);

    procedureText.setFontFamily(font);
    procedureText.setFontSize(fontSize);

    resultRun.setFontFamily(font);
    resultRun.setFontSize(bigFontSize);

    resultText.setFontFamily(font);
    resultText.setFontSize(fontSize);

    materialRun.setFontFamily(font);
    materialRun.setFontSize(bigFontSize);

    materialText.setFontFamily(font);
    materialText.setFontSize(fontSize);

  }

  void createHeader(String competitionName, String num) {
    classNum = num;

    if (!num.toUpperCase().startsWith("HP"))
      num = "HP" + num;
    else
      num = num.toUpperCase();

    title.setSpacingLineRule(LineSpacingRule.AT_LEAST);
    title.setSpacingBeforeLines(20);

    titleRun.setText("Chapter 619");
    titleRun.addBreak();
    titleRun.setText("Project Name: " + competitionName);
    titleRun.addBreak();
    titleRun.setText("Classification Number: " + num);
    titleRun.addBreak();


    filename = "gen/" + competitionName + ".docx";
  }

  void createProblem(String problem) {
    problemRun.setBold(true);
    problemRun.setText("Problem:");
    problemRun.addBreak();

    problemText.addTab();
    problemText.setText(problem);
    problemText.addBreak();
  }

  void createProcedure(String p) {
    procedure = p;

    procedureRun.setBold(true);
    procedureRun.setText("Procedure: ");
    procedureRun.addBreak();

    procedureText.addTab();
    procedureText.setText(p);
    procedureText.addBreak();
  }

  void createResult(String result) {
    resultRun.setBold(true);
    resultRun.setText("Result: ");
    resultRun.addBreak();

    resultText.addTab();
    resultText.setText(result);
    resultText.addBreak();
  }

  void createMaterials(ArrayList<String> list) {
    materialRun.setBold(true);
    materialRun.setText("Bill of Materials: ");
    materialRun.addBreak();

    for (String a : list) {
      materialText.addTab();
      materialText.setText("\u2022   ");
      materialText.setText(a);
      materialText.addBreak();
    }
  }
  void saveDoc() {
    File directory = new File("gen");
    boolean result = false;

    if (!directory.exists()) {
      try {
        directory.mkdir();
        result = true;
      } catch (SecurityException s) {
        System.out.println("Error creating directory, create it yourself.");
      }
      if (result)
        System.out.println("Directory 'gen' created.");
    }

    File document = new File(filename);
    OutputStream stream = null;

    try {
      stream = new FileOutputStream(document);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
    try {
      doc.write(stream);
    } catch (IOException e) {
      System.out.println("IOException writing file.");
    }
    try {
      doc.close();
      System.out.println("File saved.");
    } catch (IOException e) {
      System.out.println("IOException closing document.");
    }
  }
}
