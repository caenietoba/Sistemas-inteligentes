/*Insertar en un arbol*/

insertar(X,[],[[],X,[]]). 
insertar(X,[I,R,D],[MI,R,D]) :- X < R, insertar(X,I,MI).
insertar(X,[I,R,D],[I,R,MD]) :- X > R, insertar(X,D,MD).
insertar(X,[_,X,_],[_,X,_]).