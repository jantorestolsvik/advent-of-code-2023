(ns day03-test
  (:require [clojure.test :as t]
            [day03 :refer :all]))

(def puzzle-data (slurp "resources/day03.txt"))

(t/deftest part-1-test
  (t/is (= 4361 (part1 "467..114..\n...*......\n..35..633.\n......#...\n617*......\n.....+.58.\n..592.....\n......755.\n...$.*....\n.664.598..")))
  (t/is (= 546563 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 467835 (part2 "467..114..\n...*......\n..35..633.\n......#...\n617*......\n.....+.58.\n..592.....\n......755.\n...$.*....\n.664.598..")))
  (t/is (= 91031374 (part2 puzzle-data))))
