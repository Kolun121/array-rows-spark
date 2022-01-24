/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mospolytech.arrayrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import scala.Tuple2;

/**
 *
 * @author nicol
 */
public class MatrixUtils {
   public static List<Tuple2<Integer, List<Integer>>> getMatrixFromFile(String fileName) throws FileNotFoundException, NumberFormatException {
         Scanner sf = new Scanner(new File(fileName));

         List<Tuple2<Integer, List<Integer>>> matrix = new ArrayList<>();
         
         
         int rowNum = 0;
         while (sf.hasNext()) {
            String inputValue = sf.nextLine();
            
            
            String[] line = inputValue.split("\\s+");
            
            Tuple2<Integer, List<Integer>> tuple2 = new Tuple2(rowNum + 1, new ArrayList<Integer>());
            
            for (String num : line) {
               try{
                  tuple2._2.add(Integer.parseInt(num.trim()));
               } catch (NumberFormatException e){
                  throw new IllegalArgumentException("Не удалось загрузить матрицу с файла " + fileName +". Неверный формат данных - " + num + ".");
               }
            }
            
            matrix.add(tuple2);
            rowNum++;
         }
         
         return matrix;
   }
   
   public static Tuple2<Integer, Integer> countPosNum(Tuple2<Integer, List<Integer>> arg){
      Integer sum = 0;
      
      for(Integer i: arg._2){
         if(i > 0){
            sum++;
         }
      }
      
      return new Tuple2<>(arg._1, sum);
   }
}
