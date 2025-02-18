package com.csvproblems.intermediateproblems.modifycsvfile;

import java.io.*;

public class ModifyCSVFile {
    public static void writeToFile(String newFile, String[] fields, BufferedWriter bw) {
        try {
            int salary = Integer.parseInt(fields[3].trim());
            salary = salary + (salary*10)/100;
            int id = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            String department = "IT";

            String idS = Integer.toString(id);
            String nameS = name;
            String departmentS = department;
            String salaryS = Integer.toString(salary);

            String line = idS + "," + nameS + "," + departmentS + "," + salaryS;
            bw.write(line);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDetails(String employeeFile, String newFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(employeeFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
            String line;
            boolean isHeader = true;

            while((line=br.readLine())!=null) {
                if(isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if(fields.length > 3) {
                    String department = fields[2].trim();

                    if(department.equalsIgnoreCase("IT")) {
                        writeToFile(newFile, fields, bw);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String employeeFile = "src/main/java/com/csvproblems/intermediateproblems/modifycsvfile/employeefile.csv";
        String newFile = "src/main/java/com/csvproblems/intermediateproblems/modifycsvfile/newfile.csv";

        updateDetails(employeeFile, newFile);
    }
}
