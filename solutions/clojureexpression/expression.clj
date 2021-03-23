(defn operation [oper]
  (fn [& operands]
    (fn [value]
      (apply oper (mapv (fn [currentOperand] (currentOperand value)) operands)))))

(defn constant [const] (constantly const))

(defn variable [curVariable]
  #(% curVariable))

(commet ":NOTE: copy-paste code for operation declaration (at least pass of `apply`)")

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))

(def divide (operation (fn [left right]
                         (/ (double left) (double right)))))

(def negate (operation -))

(def pw (operation (fn [a b]
                     (Math/pow a b))))
(def lg (operation (fn [a b]
                     (/ (Math/log (Math/abs b)) (Math/log (Math/abs a))))))

(def ops
  {'+      add
   '-      subtract
   '*      multiply
   '/      divide
   'pw pw
   'lg lg
   'negate negate
   })

(defn parse [expr]
  (cond
    (number? expr) (constant expr)
    (symbol? expr) (variable (str expr))
    (list? expr) (apply (ops (first expr)) (map parse (rest expr)))))

(def parseFunction (comp parse read-string))

