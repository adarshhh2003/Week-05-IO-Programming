package com.csvproblems.intermediateproblems.sortcsvrecords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortCSVRecords {
    public static void sortRecordBySalary(String filePath) {
        List<String[]> records = new ArrayList<>();
        String header = "";
      try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
          String line;
          boolean isHeader = true;

          while((line= br.readLine())!=null) {
              if(isHeader) {
                  header = line;
                  isHeader = false;
                  continue;
              }

              String[] fields = line.split(",");

              if(fields.length >= 3) {
                  records.add(fields);
              }
          }
      } catch (IOException e) {
          System.out.println("Error reading the file: " + e.getMessage());
          return;
      }

      records.sort((a,b) -> {
          try {
              double salaryA = Double.parseDouble(a[3].trim());
              double salaryB = Double.parseDouble(b[3].trim());
              return Double.compare(salaryB, salaryA); // Descending order
          } catch (NumberFormatException e) {
              return 0;
          }
      });

        System.out.println(header);
        for(int i=0; i<Math.min(5, records.size()); i++) {
            System.out.println(String.join(",", records.get(i)));
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/intermediateproblems/sortcsvrecords/employees.csv";
        sortRecordBySalary(filePath);
    }
}
