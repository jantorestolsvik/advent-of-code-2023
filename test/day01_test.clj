(ns day01-test
  (:require [clojure.test :as t]
            [day01 :as day01]))

(def puzzle-data (slurp "resources/day01.txt"))

(t/deftest treat-line-test
  (t/is (= 12 (day01/treat-line "1abc2")))
  )

(t/deftest part-1-test
  (t/is (= 142 (day01/part1 "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet")))
  (t/is (= 53386 (day01/part1 puzzle-data))))


(t/deftest part-2-test
  (t/is (= 281 (day01/part2 "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen")))
  (t/is (= 53312 (day01/part2 puzzle-data)))
  )