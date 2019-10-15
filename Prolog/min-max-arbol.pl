/*Mínimo y máximo de un arbol*/

min([[],R,_],R).
min([[I,R,D],_,_],M) :- min([I,R,D],M).

max([_,R,[]],R).
max([_,_,[I,R,D]],M) :- max([I,R,D],M).