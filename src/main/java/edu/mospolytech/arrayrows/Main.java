package edu.mospolytech.arrayrows;

import java.io.PrintWriter;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class Main {
   
   public static void main(String[] args) throws Exception{
      CountPositiveServiceImpl countPositiveServiceImpl = new CountPositiveServiceImpl();
      
      if(args.length == 0){
         throw new RuntimeException("Не указан путь к файлу");
      }
      
      String path = args[0];
      
      SparkConf sparkConf = new SparkConf().setAppName("spark_array_rows").setMaster("local[*]");
      
      JavaSparkContext sc = new JavaSparkContext(sparkConf);
      
      List<Tuple2<Integer, List<Integer>>> matrix = MatrixUtils.getMatrixFromFile(path);
      
      JavaPairRDD<Integer, List<Integer>> rdd = sc.parallelizePairs(matrix);
      
      
      List<Tuple2<Integer, Integer>> result = countPositiveServiceImpl.getPositiveNumbersCount(rdd);
 

      try(PrintWriter writer = new PrintWriter("output.txt", "UTF-8");) {

         for (Tuple2<Integer, Integer> row : result) {
            writer.println(row._1 + " - номер строки. " + row._2 + " - количество положительных чисел");
         }
      } catch (Exception e) {
         throw new RuntimeException("Ошибка при попытке записать в файл output.txt");
      }
   }
}
