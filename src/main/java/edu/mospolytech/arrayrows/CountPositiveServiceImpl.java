package edu.mospolytech.arrayrows;

import java.util.List;
import org.apache.spark.api.java.JavaPairRDD;
import scala.Tuple2;


public class CountPositiveServiceImpl {
   public List<Tuple2<Integer, Integer>> getPositiveNumbersCount(JavaPairRDD<Integer, List<Integer>> lines){
      return lines.map(MatrixUtils::countPosNum).collect();
   }
}
