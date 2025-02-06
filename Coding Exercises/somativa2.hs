{- Disciplina: Programação Funcional -}
{- Curso: Bacharelado em Ciência da Computação -}
{- Docente: Frank Coelho de Alcantara -}
{- Aluno: Leonardo Ribeiro Schaedler -}
{-
Para conseguir todos os pontos referentes a este trabalho, você precisa implementar em Haskell uma  versão  que  trabalhe,  no  mímimo  com  dados  compostos  de  números inteiros e strings dos seguintes algoritmos:
    1. Merge Sort
    2. Heap Sort
    3. Binary Search Tree
    4. Deph-First Search
Se você achar mais fácil poderá fazer duas versões de cada algoritmo, uma para inteiros e outra para strings.
-}

module Main where

import Data.Graph
import Data.List

{- Os algoritmos foram desenvolvidos na ordem exposta no arquivo base: -}

{- Merge Sort -}
merge :: Ord  a=> [a] -> [a] -> [a]
merge x [] = x
merge [] y = y
merge (x:xs) (y:ys)
   | x <= y = x : merge xs (y:ys)
   | otherwise = y : merge (x:xs) ys

mergeSort :: Ord a => [a] -> [a]
mergeSort [] = []
mergeSort [a] = [a]
mergeSort x = merge (mergeSort (metade1)) (mergeSort (metade2))
   where
      metade1 = take (div (length x) 2) x
      metade2 = drop (div (length x) 2) x

{- Heap Sort -}
swap :: Int -> Int -> [a] -> [a]
swap i j xs
   | i == j = xs
   | otherwise = (take a xs) ++ (xs !! b) : (take (b-a-1) (drop (a+1) xs)) ++ (xs !! a) : (drop (b+1) xs)
     where
        a = if i < j then i else j
        b = if i < j then j else i

largest :: Ord a => Int -> Int -> [a] -> Int
largest i hs xs
   | (r < hs) && ((xs !! r) > (xs !! large)) = r
   | otherwise = large
      where
         l = 2 * i + 1
         r = 2 * i + 2
         large = larg l i hs xs

larg l i hs xs
   | (l < hs) && ((xs !! l) > (xs !! i)) = l
   | otherwise = i

heapify :: Ord a => Int -> Int -> [a] -> [a]
heapify i hs xs
   | (large /= i) = heapify large hs (swap large i xs)
   | otherwise = xs
      where large = largest i hs xs

hpsort' :: Ord a => Int -> [a] -> [a]
hpsort' 0 xs = heapify 0 (length xs) xs
hpsort' i xs = hpsort' (i - 1) (heapify i (length xs) xs)

hpsort :: Ord a => Int -> [a] -> [a]
hpsort i xs
   | i /= 1 = hpsort (i - 1) (heapify 0 i (swap 0 i xs))
   | otherwise = (heapify 0 i (swap 0 i xs))

heapsort :: Ord a => [a] -> [a]
heapsort xs = hpsort (length xs - 1) (hpsort' (length xs `div` 2) xs)

{- Binary Search Tree -}
data Arvore a = Vazio | No (Arvore a) a (Arvore a)
   deriving (Ord, Eq,  Show)

gerarArvore :: Ord a => [a] -> Arvore a
gerarArvore [] = Vazio
gerarArvore (x:xs) = gerarArvoreAux (No Vazio x Vazio) xs
   where
      gerarArvoreAux y [] = y
      gerarArvoreAux y (x:xs) = gerarArvoreAux (inserir y x) xs

inserir :: Ord a => Arvore a -> a -> Arvore a
inserir Vazio x = No Vazio x Vazio
inserir (No n1 v n2) x
   | v == x = No (inserir n1 x) v n2
   | v < x = No n1 v (inserir n2 x)
   | v > x = No (inserir n1 x) v n2

preOrdem :: Ord a => Arvore a -> [a]
preOrdem Vazio = []
preOrdem (No n1 v n2) = v : preOrdem n1 ++ preOrdem n2

buscaArvore :: Ord a => Arvore a -> a -> Arvore a
buscaArvore Vazio _ = Vazio
buscaArvore (No n1 v n2) x
   | v == x = (No n1 v n2)
   | x  < v = buscaArvore n1 x
   | x  > v = buscaArvore n2 x


{- Deph-First Search -}
depth :: Graph -> Int -> [Int]
depth graph n = depthAux (edges graph) (getEdges (edges graph) n) n (getVisited (length (vertices graph) ))

depthAux :: [Edge] -> [Edge] -> Int -> [Bool] -> [Int]
depthAux eg x n v
   | ((isVisited v n) == False) && (isEmpty noVisited) = [n]
   | ((isVisited v n) == False) && (not (isEmpty noVisited)) = n : depthAux eg x n (markVisited v n)
   | length noVisited > 0 = (depthAux eg removeNextEdges next v) ++ (depthAux eg (tail x) n (depthAuxBool eg removeNextEdges next (markVisited v next)))
   | otherwise = []
      where
         noVisited = removeVisited x v
         next = getNext (head x)
         removeNextEdges = (removeVisited (getEdges eg next) v)

depthAuxBool :: [Edge] -> [Edge] -> Int -> [Bool] -> [Bool]
depthAuxBool eg x n v
   | ((isVisited v n) == False) && (isEmpty noVisited) = v
   | ((isVisited v n) == False) && (not (isEmpty noVisited)) = depthAuxBool eg x n (markVisited v n)
   | length noVisited > 0 = (depthAuxBool eg (tail x) n (depthAuxBool eg removeNextEdges next (markVisited v next)))
   | otherwise = v
      where
         noVisited = removeVisited x v
         next = getNext (head x)
         removeNextEdges = (removeVisited (getEdges eg next) v)


markVisited :: [Bool] -> Int -> [Bool]
markVisited (x:xs) 0 = True : xs
markVisited (x:xs) n = x : markVisited xs (n-1)

isEmpty :: [Edge] -> Bool
isEmpty [] = True
isEmpty _ = False

isVisited :: [Bool] -> Int -> Bool
isVisited v n = v !! n

removeVisited :: [Edge] -> [Bool] -> [Edge]
removeVisited [] _ = []
removeVisited (h:t) v
   | v !! (getNext h) == True = removeVisited t v
   | otherwise = h : removeVisited t v

getEdges :: [Edge] -> Int -> [Edge]
getEdges [] _ = []
getEdges (h:t) n
   | getVertice h == n = h : getEdges t n
   | otherwise = getEdges t n

getVertice (x,y) = x
getNext (x,y) = y

getVisited :: Int -> [Bool]
getVisited 0 = []
getVisited x = False : getVisited (x-1)

-- gera o grafo
grafo = buildG (0,3) [(0,1),(0,2),(1,2),(2,3),(2,0),(3,3)]

main :: IO()
main = do
   print("Merge Sort")
   print(mergeSort [1,5,89,6,32,1,4,4,2,5,1,2])
   print(mergeSort [1,5,90,52,41,52,68,5,2,6])
   print(mergeSort ['d','f','a','c','i','b'])
   print(mergeSort ['u','s','w','q','n','d'])

   print("Heap Sort")
   print(heapsort [1,5,89,6,32,1,4,4,2,5,1,2])
   print(heapsort [1,5,90,52,41,52,68,5,2,6])
   print(heapsort ['d','f','a','c','i','b'])
   print(heapsort ['u','s','w','q','n','d'])

   print("Binary Search Tree")
   print(preOrdem $ buscaArvore ( gerarArvore [10,30,50,40,80,70]) 30)
   print(preOrdem $ buscaArvore ( gerarArvore ['j','k','a','i','l']) 'k')

   print("Depth-First-Search")
   print(depth grafo 0)
   print(depth grafo 2)
   print(depth grafo 1)
