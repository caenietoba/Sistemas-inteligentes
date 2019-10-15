/*Altura de un arbol*/

altura([],0).
altura([I,_,D],N) :- altura(I,NI),altura(D,ND),max(NI,ND,M),N is M+1.