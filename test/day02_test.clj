(ns day02-test
  (:require [clojure.test :as t]
            [day02 :refer :all]))

(def puzzle-data (slurp "resources/day02.txt"))

(t/deftest part-1-parse-line
  (t/is
    (=
      {:id     12
       "green" 14
       "red"   18
       "blue"  5
       }
      (parse-line "Game 12: 5 blue, 12 green, 12 red; 11 green, 3 red; 14 green, 3 blue, 18 red\n"))
    )
  )

(t/deftest part-1-test
  (t/is (= 8 (part1 "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\nGame 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\nGame 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")))
  (t/is (= 2369 (part1 puzzle-data))))

(t/deftest part-2-test
  (t/is (= 2286 (part2 "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\nGame 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\nGame 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")))
  (t/is (= 66363 (part2 puzzle-data))))
