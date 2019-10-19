/*Insertar en un arbol*/

insertar(X,[],[[],X,[]]). 
insertar(X,[I,R,D],[MI,R,D]) :- X < R, insertar(X,I,MI).
insertar(X,[I,R,D],[I,R,MD]) :- X > R, insertar(X,D,MD).
insertar(X,[_,X,_],[_,X,_]).

%% http://ferestrepoca.github.io/paradigmas-de-programacion/proglogica/tutoriales/prolog-gh-pages/index.html#12-ejemplo-aut%C3%B3matas