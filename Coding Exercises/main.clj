; praticamente tudo que escrevemos em clojure tem a forma de uma s-expression
; (operador operando operando ...)
(println "Hello World!")
(println (+ 4 3))
;; os comandos são dados e estes dados são do tipo lista
(println (+ 1 2 3 4 (* 3 4)))

;; namespace é um conceito forte em clojure tudo que fazemos está em um 
;; namespace (ns determineAquioNome)

;; repl read-eval-print loop

;; as keywords que conhecemos são referidas como expressões. Por que existem keywords que 
;; equivalentes a variáveis e symbols que são equivalentes a variáveis, melhor identificadores
;; que identificarão ações.
(require '[clojure.string :as str])
(println (str/upper-case "coisa"))

(println "o valor de pi : " Math/PI " e o seno de 30; " (Math/sin (/ Math/PI 6)))

(def treco 4.5)
(println "treco vale: " treco)

(def treco2 (+ 1 2 3 4 (* 3 4)))
(println "treco2 vale: " treco2)

;; uma variável para o valor 30 e calcular todas a indentidades trigonométricas

(def trinta (/ Math/PI 6)) ;; o uso de def cria uma variável globla
(println (Math/sin trinta))
(println (Math/cos trinta))
(println (Math/tan trinta))

;; conjunto de tipos primitivos
;; short ... números pequenos -32k e 32k
;; int .... números ... -2 bilhões... até 2 bilhões ...
;; long ... números inteiros grandes.... https://www.tutorialspoint.com/clojure/clojure_data_types.htm
;; float, double, char, boolean e string

;; operador importante: fn  cria um objeto função que vai devolver um valor pode ser
;; utilizado como uma variável, como uma coleção (lista, vetor, etc.. ) ou pode ser 
;; argumento de outra função... lembra muito a função lambda por que é uma função anônima. 

;;((fn [argumentos] corpo ) operandos )

((fn [texto] (println texto)) "grande coisa")

(println (#(+ %1 %2) 45 5))

(def soma (fn [a b] (println 
" A soma de " a " e " b "resulta em: " (+ a b))
))
(soma 30 1)

;; defn nome[lista de argumentos] (corpo da função)
(defn oi [nome] (str "bem-vindo " nome "!!!"))
(println (oi "frank"))

(defn soma2 [a b] (println 
" A soma de " a " e " b "resulta em: " (+ a b)))
(soma2 2 3)

;; def define um indentificador que é global em um determinado namesapce,
;; isso é uma prática ruim em programação (variáveis globais)
;; uma alternativa é usar a expressão let, que vai definir um identificador
;; local em um determinado escopo. Ou seja use o let para definir, funções e variáveis
;; que existem apenas em um escopo específico. 

(defn subtrai [a b]
  (let [resultado (- a b)] ;; sintaxe é importante
  (println "A subtracao de " b "em " a "resulta em: " resultado)
  );; fecha o let
);; fecha subrai

(subtrai 10 4)
;;(println resultado) ;; resultado não existe fora da função subtrai por isso da erro

(defn sub [a b]
  (def result (- a b))
  (println "result ;" result)
  result
);;

(sub 3 4)
(println result) ;; usando def os identificadores são globais.

(defn mensagem [msg]
  (let [
      a 17
      b 15
      c (clojure.string/upper-case msg)
    ]
    (println a b c)
  );; fim do let
);; fim de mensagem

(mensagem "outro treco estranho")

(defn contaargumento
  ([] 0)
  ([x] 1)
  ([x y] 2)
  ([x y & more] (+ (contaargumento x y) (count more)))
) ;;

(println (contaargumento 1))
(println (contaargumento 1 2))
(println (contaargumento 1 2 3 4 5 6))

;;;;;;; COLEÇÕES DE VALORES ;;;; 

;; vetores, vector: uma lista ORDENADA de valores. Qualquer valor pode ser colocado nesta sequência 

;; conjunto, set: uma lista NÃO ORDEANADA de valores. Conjuntos não premitem 
;; repetições de itens... isso implica em permitir operações de conjuntos como 
;; união, interseção... etc.

;; lista, list: muito parecida com o vetor. Listas são mais lentas. 
;; usamos listas em lugar de vetores apenas se for necessário adicionar elementos
;; na primeira posição da estrutura de dados. Se considerarmos desepenho. 

;; map, map: uma sequencia de pares key/value. Você pode, e deve usar, keywords, 
;; ou symbols, como key.... symblos são mais rápidos. 

;; todas estas coleções permitem a inclusão de elememtos do mesmo tipo da coleção.

(def vetor [1.1 1.2 1.3 4.5])
(def lista '(1.1 1.2 1.3 4.5))
(def conjunto #{1.1 1.2 1.3 4.5})
(def mapa {:a 1.1 :b 1.2 :c 1.3 :d 4.5})

(println "vetor: " vetor)
(println "lista: " lista)
(println "conjunto: " conjunto)
(println "mapa: " mapa)

;; listas são sequencias seq isto quer dizer que podemos transformar as outras coleçõem em listas. 

(println (seq vetor))
(println (seq conjunto))
(println (seq mapa))

;; podemos acessar os elementos de cada coleção
(println "indice 0 do vetor" (vetor 0))

(println "a chave a do mapa " (mapa :a))

;; conjuntos não são ordenados... então fica um pouco mais dificil de encontrar oe elementos
(def alunos #{"bea", "ana", "cal"})
(println alunos)

;; todas as estruturas de dados são imutaveis. 
(def alunos2 (conj alunos "paula"))
(println alunos2)

(def alunos3 (conj alunos "marcia" "wanda"))
(println alunos3)

;; tirar elementos do comjunot
(def alunos4 (disj alunos3 "marcia"))
(println alunos4)

(println (contains? alunos4 "joana"))
(println (contains? alunos4 "bea"))

;;;; VETORES ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def estranho [ 1 "coisa" :valor ["treco" 2]])
(println "o vetor" estranho)

(println (get estranho 0))
(println (get estranho 3))
(println (get estranho 13))

;; para incluir elementos em um vetor podemos usar conj ou into, use conj

(println "o tempo de inclusão com conj")
(time (conj [1 2] 3))
(println "o tempo de inclusão com into")
(time (into [1 2] [3]))

;; tirar o último elemento 
(println (pop estranho))

(println (vector-of :int 5 6 120))

;; subvec devolve um subvetor
(println (subvec (vector 1 2 3 4 5 6 ) 2 4))

(println (seq (vector \a \v \c \b)))

;; expressões first devolvo primento de uma lista e rest devolve tudo na lista menos o primeiro elemento

(println (first (seq (vector \a \v \c \b))))
(println (rest (seq (vector \a \v \c \b))))

;;;; tem estruturas de contole de flux

;; (if condicional them else)

(println (if true "verdadeiro" "falso"))
(println (if false "verdadeiro" "falso"))

;; a estrutura para o else é opcional 
(println (if false "verdadeiro"))

;; falso em clujure ou é false ou é nil. Qualquer outro valor tem o valor true se for avaliado 
;; logicamente. 

(println (if nil "verdadeiro" "falso"))
(println (if [nil] "verdadeiro" "falso"))
(println (if (first [nil]) "verdadeiro" "falso"))
(println (if [] "verdadeiro" "falso"))


(if (even? 5)
  (do (println "par") true)
  (do (println "impar") false)
)

;; no clojure temos listas lazy, como no Haskell, o que nos permite gerar listas infinitas e 
;; só utilizar os valores que são necessários, a lista só é criada em memória até o ponto necessário. 

(def fibonacci1
  ((fn fib [a b] 
     (lazy-seq (cons a (fib b (+ a b)))))
   0 1))

(println (take 30 fibonacci1))