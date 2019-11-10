hombre(camilo).
hombre(jose).
hombre(ivan).
hombre(francisco).
hombre(daniel).
hombre(jaime).
hombre(jaime2).
hombre(carlos).
hombre(jaime_jr).
hombre(juan_jose).
hombre(jaime_andres).
hombre(david).
hombre(dmitri).
hombre(juan_mateo).
hombre(santiago).
hombre(alfredo).

mujer(luisa).
mujer(stella).
mujer(gloria).
mujer(magda).
mujer(maritza).
mujer(gladis).
mujer(maria_claudia).
mujer(sandra).
mujer(ivonne).

padre(camilo, jose).
padre(daniel, jose).
padre(francisco, jose).
padre(ivan, jose).
padre(jose, jaime).
padre(jaime_jr, jaime).
padre(magda, jaime).
padre(dmitri, francisco).
padre(juan_jose, jaime_jr).
padre(jaime_andres, jaime_jr).
padre(juan_mateo, carlos).
padre(santiago, carlos).
padre(david, alfredo).
padre(luisa, jaime2).
padre(carlos, jaime2).
padre(maritza, jaime2).

madre(camilo, luisa).
madre(daniel, luisa).
madre(francisco, luisa).
madre(ivan, luisa).
madre(jose, stella).
madre(jaime_jr, stella).
madre(magda, stella).
madre(dmitri, ivonne).
madre(juan_jose, sandra).
madre(jaime_andres, sandra).
madre(juan_mateo, maria_claudia).
madre(santiago, maria_claudia).
madre(david, maritza).
madre(luisa, gloria).
madre(carlos, gloria).
madre(maritza, gloria).

hermanos(X, Z) :-
    madre(X, Y),
    madre(Z, Y),
    X \= Z.

hijos(X, Z) :- madre(Z, X).
hijos(X, Z) :- padre(Z, X).

primos(X, Z) :-
    padre(X, W),
    hermanos(W, W1),
    hijos(W1, Z).
primos(X, Z) :-
    madre(X, W),
    hermanos(W, W1),
    hijos(W1, Z).

abuelos(X, Z) :- abuelos_paterno(X, Z).
abuelos(X, Z) :- abuelos_materno(X, Z).

abuelos_paterno(X, Z) :- padre(X, Y), padre(Y, Z).
abuelos_paterno(X, Z) :- padre(X, Y), madre(Y, Z).

abuelos_materno(X, Z) :- madre(X, Y), padre(Y, Z).
abuelos_materno(X, Z) :- madre(X, Y), madre(Y, Z).

sobrinos(X, Z) :- hermanos(X, Y), hijos(Y, Z).

tios(X, Z) :- madre(X, Y), hermanos(Y, Z).
tios(X, Z) :- padre(X, Y), hermanos(Y, Z).

conyuges(X, Z) :-
    hijos(X, Y),
    madre(Y, Z),
    X \= Z.
conyuges(X, Z) :-
    hijos(X, Y),
    padre(Y, Z),
    X \= Z.

nietos(X, Z) :- hijos(X, Y), hijos(Y, Z).

familiares(X, Z) :- madre(X, Z).
familiares(X, Z) :- padre(X, Z).
familiares(X, Z) :- hermanos(X, Z).
familiares(X, Z) :- hijos(X, Z).
familiares(X, Z) :- nietos(X, Z).
familiares(X, Z) :- abuelos(X, Z).
familiares(X, Z) :- primos(X, Z).
familiares(X, Z) :- sobrinos(X, Z).
familiares(X, Z) :- tios(X, Z).

nueras(X, Z) :-
    hijos(X, Y),
    conyuges(Y, Z),
    mujer(Z).

yernos(X, Z) :-
    hijos(X, Y),
    conyuges(Y, Z),
    hombre(Z).

suegros(X, Z) :- conyuges(X, Y), padre(Y, Z).
suegros(X, Z) :- conyuges(X, Y), madre(Y, Z).

nofamiliares(X, Z) :- nueras(X, Z).
nofamiliares(X, Z) :- yernos(X, Z).
nofamiliares(X, Z) :- suegros(X, Z).
nofamiliares(X, Z) :- hermanos(X, Y), conyuges(Y, Z).
nofamiliares(X, Z) :- conyuges(X, Z).
























