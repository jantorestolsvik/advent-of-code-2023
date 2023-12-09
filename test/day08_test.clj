(ns day08-test
  (:require [clojure.test :as t]
            [day08 :refer :all]))

(def puzzle-data (slurp "resources/day08.txt"))

(t/deftest part-1-test
  (t/is (= 2 (part1 "RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)")))
  (t/is (= 6 (part1 "LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)")))
  (t/is (= 22357 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 6 (part2 "LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)")))
  (t/is (= 10371555451871 (part2 puzzle-data))))
