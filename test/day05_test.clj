(ns day05-test
  (:require [clojure.test :as t]
            [day05 :refer :all]))

(def puzzle-data (slurp "resources/day05.txt"))

(t/deftest parse-map-test
  (t/is
    (=
      ["humidity-to-location map:" [[[56 57] 60], [[93 96] 56]]]
      (parse-map "humidity-to-location map:\n60 56 2\n56 93 4"))
    )
  )

(t/deftest part-1-test
  (t/is (= 35 (part1 "seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4")))
  (t/is (= 26273516 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 46 (part2 "seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4")))
  (t/is (= 34039469 (part2 puzzle-data))))
