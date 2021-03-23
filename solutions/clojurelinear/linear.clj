(defn operation [f] (partial mapv f))

(defn operationToLine [f]
  #(mapv (fn [line]
           (f line %2)) %1))

(defn operationToElem [f]
  #(mapv (fn [comp]
           (f comp %2)) %1))

(defn minor [a b i j]
  (- (* (nth a i) (nth b j))
     (* (nth a j) (nth b i))))

(defn enclosedOperation [f]
  (fn evaluate [a b]
    (if (number? a)
      (f a b)
      (mapv evaluate a b))))

(def v+ (operation +))
(def v- (operation -))
(def v* (operation *))

(defn scalar [a b]
  (reduce + (v* a b)))

(defn vect [a b]
  [(minor a b 1 2) (minor a b 2 0) (minor a b 0 1)])

(def v*s
  (operationToElem *))

(def m+ (operation v+))
(def m- (operation v-))
(def m* (operation v*))
(def m*s (operationToLine v*s))
(def m*v (operationToLine scalar))

(defn transpose [m]
  (apply mapv vector m))

(defn m*m [a b]
  (mapv (partial m*v (transpose b)) a))

(def s+ (enclosedOperation +))
(def s- (enclosedOperation -))
(def s* (enclosedOperation *))