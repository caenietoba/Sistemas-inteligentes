/*Quitar un nodo de un árbol y mantenerlo ordenado*/

quitar([],X,[]).
quitar([I,R,[]],R,I).
quitar([[],R,D],R,D).
quitar([I,R,D],R,[AI,MI,D]) :- maximo(I,MI),quitar(I,MI,AI).
quitar([I,R,D],X,[MI,R,D]) :- X<R, quitar(I,X,MI).
quitar([I,R,D],X,[I,R,MD]) :- X>R, quitar(D,X,MD).