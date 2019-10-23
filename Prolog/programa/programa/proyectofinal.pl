%% Realizado por Raisa Gómez-Rodríguez, Rolando Rodríguez-Cruz, Miguel Echevarria-Pinedo 
%% Tomado con el fin de analisarlo

:- use_module(library(pce)).

esrespuesta('si').
esrespuesta('no').
%MOSCA BLANCA
espregunta('se ubica en el enves de la hoja ?',X) :-esrespuesta(X).
espregunta('tiene presencia de hojas picadas?',X) :-esrespuesta(X).
espregunta('tiene presencia de hongos ?',X) :-esrespuesta(X).
espregunta('tiene secreciones melosas ?',X) :-esrespuesta(X).
%POLILLA
espregunta('tiene las hojas comidas?',X) :-esrespuesta(X).
espregunta('tiene brotes comidas?',X) :-esrespuesta(X).
espregunta('tiene la medula de tallo destruida ?',X) :-esrespuesta(X).
espregunta('tiene el follaje destruida ?',X) :-esrespuesta(X).
%PULGONES
espregunta('presenta las yemas caidas ?',X) :-esrespuesta(X).
espregunta('presenta frutos peque�os?',X) :-esrespuesta(X).
espregunta('presenta brotes muertos',X) :-esrespuesta(X).
espregunta('son brotes peque�os?',X) :-esrespuesta(X).
espregunta('presenta huevecillos?',X) :-esrespuesta(X).
espregunta('baja produccion de vainas?',X) :-esrespuesta(X).
espregunta('tiene las vainas encurvadas ?',X) :-esrespuesta(X).
espregunta('tiene las hojas encrespadas?',X) :-esrespuesta(X).
espregunta('vainas con peso normal?',X) :-esrespuesta(X).
espregunta('vainas con tama�o normal?',X) :-esrespuesta(X).
espregunta('presenta ceniza blanca en hojas?',X) :-esrespuesta(X).
espregunta('enegrecimiento de brotes?',X) :-esrespuesta(X).
espregunta('enegrecimiento de flores?',X) :-esrespuesta(X).
espregunta('enegrecimiento de frutos?',X) :-esrespuesta(X).
%ACARINA
espregunta('tiene manchas blanquesinas ?',X) :-esrespuesta(X).
espregunta('tiene las hojas secas ?',X) :-esrespuesta(X).
espregunta('tiene perdida de hojas?',X) :-esrespuesta(X).
%CHINCHES
espregunta('tiene las hojas picadas?',X) :-esrespuesta(X).
espregunta('tiene las hojas encojidas?',X) :-esrespuesta(X).
%MOSCA MINADORA
espregunta('tiene las hojas minadas?',X) :-esrespuesta(X).
%QUEREZA
espregunta('presenta larvas blancas?',X) :-esrespuesta(X).
espregunta('la larva es alargada?',X) :-esrespuesta(X).
espregunta('se encuentra en las vainas?',X) :-esrespuesta(X).
espregunta('la larva es peque�a?',X) :-esrespuesta(X).
espregunta('la larva es grande?',X) :-esrespuesta(X).
espregunta('se encuentra en las ramas?',X) :-esrespuesta(X).
espregunta('se encuentra  en los tallos?',X) :-esrespuesta(X).

%PRINCIPAL
espregunta('es una larva ?',X) :-esrespuesta(X).
espregunta('ataca hojas ?',X) :-esrespuesta(X).
espregunta('presenta hongos ?',X) :-esrespuesta(X).

%% main
main:-
        new(D,dialog('SISTEMA EXPERTO DE PLAGAS DE TARA')), %% Crea la vantalla principal
        send(D,size,size(560,400)), %% Tamaño
        send(D,colour,colour(red)),
        send(D, append, new(Menu, menu_bar)), %% Añade un menu
        send(Menu, append, new(Iniciar, popup(iniciar))), %% Agrega un boton al menu
        send(Menu, append, new(Ayuda, popup(ayuda))), %% Agrega un boton al menu
        send_list(Iniciar, append, %% Agrega una lista de botones a inicio
                         [ menu_item(iniciar, message(@prolog,pp))
                         ]),
        send_list(Ayuda, append, %% Agrega unna lista de botones a ayuda
                         [ menu_item(autor, message(@display, inform, 'rolandopalermo.blogspot.com'))
                         ]),
        mostrar('C:/Users/cenb/Documents/Prolog/programa/programa/talla.bmp',D,Menu),
        send(D,open,point(200,200)). %% Abre la ventana en el punto 200, 200

%% Ventana inicial donde se pregunta de manera general que esta afectando a la planta
pp :- new(D,dialog('PREGUNTAS')), %% Crea una nueva ventana
        %% Crea 3 items de seleccion cada uno con una lista de seleccion con "si" o "no"
        new(Pre1,menu('es una larva ?')),
        send_list(Pre1,append,[si , no]),
        new(Pre2,menu('ataca hojas ?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('presenta hongos ?')),
        send_list(Pre3,append,[si,no]),
        %% Añade los 3 items
        send(D,append(Pre1)),
        send(D,append,Pre2),
        send(D,append,Pre3),

        %% Crea un boton que dirige a la sentencia principal pasandole los 3 parametros seleccionados en el item
        new(B,button(siguiente,
                and(message(@prolog,principal,Pre1?selection,Pre2?selection,Pre3?selection),
                message(D,destroy)))),
        send(D,append,B), %% Añade el boton a la ventana
        send(D,default_button,siguiente), %% Le pone la forma basica del boton
        send(D,open,point(350,350)). %% Inicia el boton en esa posición



%% Principal para larvas
principal(P1,P2,P3) :-
        espregunta('es una larva ?',P1),P1='si',
        espregunta('ataca hojas ?',P2),P2='no',
        espregunta('presenta hongos ?',P3),P3='no',
        pl.

%% Principal para cuando las hojas estan dañadas
principal(P1,P2,P3) :-
        espregunta('es una larva ?',P1),P1='no',
        espregunta('ataca hojas ?',P2),P2='si',
        espregunta('presenta hongos ?',P3),P3='no',
        ph.

%% Principal para hongos
principal(P1,P2,P3) :-
espregunta('es una larva ?',P1),P1='no',
espregunta('ataca hojas ?',P2),P2='no',
espregunta('presenta hongos ?',P3),P3='si',
pho.

%% En caso de que no entre a ningun caso (Se seleccione más de una opción)
principal(_,_,_) :-
        new(D,dialog('ERROR')),
        new(L,label(l,'ELIJA SOLO UNA OPCION',font('times','roman',16))),
        send(D,append,L),
        send(D,open,point(350,350)).

%% Se llama en caso de que se elija  hongos como el problema
pho :- new(D,dialog('PREGUNTAS')),
        %% Crea 6 items (Preguntas relacionadas con los hongos) con sus respectivas respuestas
        new(Pre1,menu('vainas con peso normal?')),
        send_list(Pre1,append,[si , no]),
        new(Pre2,menu('enegrecimiento de brotes?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('vainas con tama�o normal?')),
        send_list(Pre3,append,[si,no]),
        new(Pre4,menu('presenta ceniza blanca en hojas?')),
        send_list(Pre4,append,[si,no]),
        new(Pre5,menu('enegrecimiento de flores?')),
        send_list(Pre5,append,[si,no]),
        new(Pre6,menu('enegrecimiento de frutos?')),
        send_list(Pre6,append,[si,no]),
        %% Añade los 6 items
        send(D,append(Pre1)),
        send(D,append,Pre2),
        send(D,append,Pre3),
        send(D,append,Pre4),
        send(D,append,Pre5),
        send(D,append(Pre6)),
        %% Crea un boton para volver a la ventana de preguntas generales
        new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
        %% Crea un boton para hacer la consulta al sistema llamando a hongos y pasandole las selecciones
        new(B,button(siguiente,message(@prolog,hongos,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection))),
        send(D,append,B1), %% Agrega los botones
        send(D,append,B),
        send(D,open,point(300,300)).

%% En caso de que hallan sido seleccionadas de esa forma abre la ventana
%% de preguntas para saber que clase de plaga lo genera
hongos(P1,P2,P3,P4,P5,P6) :-
        espregunta('vainas con peso normal?',P1),P1='si',
        espregunta('enegrecimiento de brotes?',P2),P2='no',
        espregunta('vainas con tama�o normal?',P3),P3='si',
        espregunta('presenta ceniza blanca en hojas?',P4),P4='si',
        espregunta('enegrecimiento de flores?',P5),P5='no',
        espregunta('enegrecimiento de frutos?',P6),P6='no',
        ppm.

%% En caso de que hallan sido seleccionadas de esa forma abre la ventana
%% de preguntas para saber que clase de plaga lo genera
hongos(P1,P2,P3,P4,P5,P6) :-
        espregunta('vainas con peso normal?',P1),P1='no',
        espregunta('enegrecimiento de brotes?',P2),P2='si',
        espregunta('vainas con tama�o normal?',P3),P3='no',
        espregunta('presenta ceniza blanca en hojas?',P4),P4='no',
        espregunta('enegrecimiento de flores?',P5),P5='si',
        espregunta('enegrecimiento de frutos?',P6),P6='si',
        ppm.

%% En caso de que las selecciones sean diferentes es por que no conoce la respuesta
hongos(_,_,_,_,_,_) :-
        new(D,dialog('ERROR')),
        new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
        send(D,append,L),
        send(D,open,point(350,350)).

%% Preguntas sobre MOSCAS O PULGONES
ppm :-
        new(D,dialog('PREGUNTAS')), %% Crea la ventana de preguntas
        %% Crea 8 items de seleccion cada uno con una lista de seleccion con "si" o "no"
        new(Pre1,menu('tiene las hojas encrespadas?')),
        send_list(Pre1,append,[si , no]),
        new(Pre2,menu('tiene las vainas encurvadas ?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('tiene presencia de hojas picadas? ')),
        send_list(Pre3,append,[si,no]),
        new(Pre4,menu('baja produccion de vainas?')),
        send_list(Pre4,append,[si,no]),
        new(Pre5,menu('se ubica en el enves de la hoja ?')),
        send_list(Pre5,append,[si,no]),
        new(Pre6,menu('tiene secreciones melosas ?')),
        send_list(Pre6,append,[si , no]),
        new(Pre7,menu('presenta huevecillos?')),
        send_list(Pre7,append,[si,no]),
        new(Pre8,menu('presenta las yemas caidas ?')),
        send_list(Pre8,append,[si,no]),
        %% Añade los items
        send(D,append(Pre1)),
        send(D,append,Pre2),
        send(D,append,Pre3),
        send(D,append,Pre4),
        send(D,append,Pre5),
        send(D,append(Pre6)),
        send(D,append,Pre7),
        send(D,append,Pre8),

        %% Botones y los agrega
        new(B1,button(atras,and(message(@prolog,pho),message(D,destroy)))),
        new(B,button(siguiente,message(@prolog,pulmos,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection,Pre7?selection,Pre8?selection))),
        send(D,append,B1),
        send(D,append,B),
        send(D,open).

%% En caso de que esas sean las respuestas despliega la imagen de pulgones
pulmos(P1,P2,P3,P4,P5,P6,P7,P8) :-
        espregunta('tiene las hojas encrespadas?',P1),P1='si',
        espregunta('tiene las vainas encurvadas ?',P2),P2='si',
        espregunta('tiene presencia de hojas picadas?',P3),P3='no',
        espregunta('baja produccion de vainas?',P4),P4='si',
        espregunta('se ubica en el enves de la hoja ?',P5),P5='no',
        espregunta('tiene secreciones melosas ?',P6),P6='si',
        espregunta('presenta huevecillos?',P7),P7='si',
        espregunta('presenta las yemas caidas ?',P8),P8='si',
        pf2('C:/Users/cenb/Documents/Prolog/programa/programa/pulgones.jpg','PULGONES','C:/Users/cenb/Documents/Prolog/programa/programa/pulgonest.jpg').

%% En caso de que esas sean las respuestas despliega la imagen de moscas
pulmos(P1,P2,P3,P4,P5,P6,P7,P8) :-
espregunta('tiene las hojas encrespadas?',P1),P1='no',
espregunta('tiene las vainas encurvadas ?',P2),P2='no',
espregunta('tiene presencia de hojas picadas?',P3),P3='si',
espregunta('baja produccion de vainas?',P4),P4='no',
espregunta('se ubica en el enves de la hoja ?',P5),P5='si',
espregunta('tiene secreciones melosas ?',P6),P6='si',
espregunta('presenta huevecillos?',P7),P7='no',
espregunta('presenta las yemas caidas ?',P8),P8='no',
pf2('C:/Users/cenb/Documents/Prolog/programa/programa/moscab.jpg','MOSCA BLANCA','C:/Users/cenb/Documents/Prolog/programa/programa/moscabt.jpg').

%% En caso de que no entre a ninguno de los dos casos anteriores
pulmos(_,_,_,_,_,_,_,_) :-
        new(D,dialog('ERROR')),
        new(L,label(l,'ELIJA SOLO UNA OPCION',font('times','roman',16))),
        send(D,append,L),
        send(D,open,point(300,300)).

%% Se llama en caso de que se elija  hojas como el problema
ph:-
        new(D,dialog('PREGUNTAS')), %% Crea la ventana
        %% Crea 6 items
        new(Pre1,menu('tiene las hojas picadas?')),
        send_list(Pre1,append,[si , no]),
        new(Pre2,menu('tiene manchas blanquesinas ?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('tiene las hojas minadas?')),
        send_list(Pre3,append,[si,no]),
        new(Pre4,menu('tiene las hojas encojidas?')),
        send_list(Pre4,append,[si,no]),
        new(Pre5,menu('tiene las hojas secas ?')),
        send_list(Pre5,append,[si,no]),
        new(Pre6,menu('tiene perdida de hojas?')),
        send_list(Pre6,append,[si,no]),
        %% Agrega los items
        send(D,append(Pre1)),
        send(D,append,Pre2),
        send(D,append,Pre3),
        send(D,append,Pre4),
        send(D,append,Pre5),
        send(D,append(Pre6)),
        %% CRea los botones de siguiente y atras
        new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
        new(B,button(siguiente,
                    message(@prolog,hojas,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection))),
        send(D,append,B1), %% Agrega los botones
        send(D,append,B),
        send(D,open,point(300,300)).


%% En caso de que las selecciones sean estas es por que es un chinche
hojas(P1,P2,P3,P4,P5,P6) :-
        espregunta('tiene las hojas picadas?',P1),P1='si',
        espregunta('tiene manchas blanquesinas ?',P2),P2='no',
        espregunta('tiene las hojas minadas?',P3),P3='no',
        espregunta('tiene las hojas encojidas?',P4),P4='si',
        espregunta('tiene las hojas secas ?',P5),P5='no',
        espregunta('tiene perdida de hojas?',P6),P6='no',
        pf1('C:/Users/cenb/Documents/Prolog/programa/programa/chinches.jpg','CHINCHES','C:/Users/cenb/Documents/Prolog/programa/programa/miguel.bmp').

%% En caso de que las selecciones sean estas es por que es un mosca
hojas(P1,P2,P3,P4,P5,P6) :-
        espregunta('tiene las hojas picadas?',P1),P1='no',
        espregunta('tiene manchas blanquesinas ?',P2),P2='no',
        espregunta('tiene las hojas minadas?',P3),P3='si',
        espregunta('tiene las hojas encojidas?',P4),P4='no',
        espregunta('tiene las hojas secas ?',P5),P5='no',
        espregunta('tiene perdida de hojas?',P6),P6='no',
        pf1('C:/Users/cenb/Documents/Prolog/programa/programa/moscam.jpg','MOSCA MINADORA','C:/Users/cenb/Documents/Prolog/programa/programa/moscamt.bmp').

%% En caso de que las selecciones sean estas es por que es un acarina
hojas(P1,P2,P3,P4,P5,P6) :-
        espregunta('tiene las hojas picadas?',P1),P1='no',
        espregunta('tiene manchas blanquesinas ?',P2),P2='si',
        espregunta('tiene las hojas minadas?',P3),P3='no',
        espregunta('tiene las hojas encojidas?',P4),P4='no',
        espregunta('tiene las hojas secas ?',P5),P5='si',
        espregunta('tiene perdida de hojas?',P6),P6='si',
        pf2('C:/Users/cenb/Documents/Prolog/programa/programa/acarina.jpg','ACARINA','C:/Users/cenb/Documents/Prolog/programa/programa/acarinat.bmp').

%% En caso de que no entre a ninguna de las anteriores no puede determinar la plaga
hojas(_,_,_,_,_,_) :-
        new(D,dialog('ERROR')),
        new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
        send(D,append,L),
        send(D,open,point(350,350)).

%% Ventana de preguntas en caso de que se selecciones larvas como problema general
pl:-new(D,dialog('PREGUNTAS')),
        new(Pre1,menu('presenta   larvas   blancas?')), %% Crea la ventana
        %% Crea 10 items con preguntas y sus respuestas
        send_list(Pre1,append,[si , no]),
        new(Pre2,menu('la larva es grande?                 ')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('tiene las hojas comidas?            ')),
        send_list(Pre3,append,[si,no]),
        new(Pre4,menu('tiene la medula de tallo destruida ?')),
        send_list(Pre4,append,[si,no]),
        new(Pre5,menu('la larva es alargada?               ')),
        send_list(Pre5,append,[si,no]),
        new(Pre6,menu('se encuentra en las vainas?         ')),
        send_list(Pre6,append,[si , no]),
        new(Pre7,menu('se encuentra en las ramas?          ')),
        send_list(Pre7,append,[si,no]),
        new(Pre8,menu('se encuentra  en los tallos?        ')),
        send_list(Pre8,append,[si,no]),
        new(Pre9,menu('tiene brotes comidas?               ')),
        send_list(Pre9,append,[si,no]),
        new(Pre10,menu('tiene el follaje destruida ?       ')),
        send_list(Pre10,append,[si,no]),
        %% Agrega los 10 items
        send(D,append(Pre1)),
        send(D,append,Pre2),
        send(D,append,Pre3),
        send(D,append,Pre4),
        send(D,append,Pre5),
        send(D,append(Pre6)),
        send(D,append,Pre7),
        send(D,append,Pre8),
        send(D,append,Pre9),
        send(D,append,Pre10),
        %% Crea los botones de consulta o atras
        new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
        new(B,button(siguiente,message(@prolog,larvas,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection,Pre7?selection,Pre8?selection,Pre9?selection,Pre10?selection))),
        send(D,append,B1), %% Agrega los botones
        send(D,append,B),
        send(D,open,point(300,300)).

%% En caso de que las selecciones sean estas es por que es un pinnaspis
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10) :-
        espregunta('presenta larvas blancas?',P1),P1='si',
        espregunta('la larva es grande?',P2),P2='no',
        espregunta('tiene las hojas comidas?',P3),P3='no',
        espregunta('tiene la medula de tallo destruida ?',P4),P4='no',
        espregunta('la larva es alargada?',P5),P5='si',
        espregunta('se encuentra en las vainas?',P6),P6='si',
        espregunta('se encuentra en las ramas?',P7),P7='no',
        espregunta('se encuentra  en los tallos?',P8),P8='no',
        espregunta('tiene brotes comidas?',P9),P9='no',
        espregunta('tiene el follaje destruida ?',P10),P10='no',
        pf3('C:/Users/cenb/Documents/Prolog/programa/programa/pinnaspis.jpg','PINNASPIS','C:/Users/cenb/Documents/Prolog/programa/programa/pinnaspist.bmp').

%% En caso de que las selecciones sean estas es por que es un ICERVA PUTCHASI
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10) :-
        espregunta('presenta larvas blancas?',P1),P1='no',
        espregunta('la larva es grande?',P2),P2='si',
        espregunta('tiene las hojas comidas?',P3),P3='no',
        espregunta('tiene la medula de tallo destruida ?',P4),P4='no',
        espregunta('la larva es alargada?',P5),P5='no',
        espregunta('se encuentra en las vainas?',P6),P6='no',
        espregunta('se encuentra en las ramas?',P7),P7='si',
        espregunta('se encuentra  en los tallos?',P8),P8='si',
        espregunta('tiene brotes comidas?',P9),P9='no',
        espregunta('tiene el follaje destruida ?',P10),P10='no',
        pf3('C:/Users/cenb/Documents/Prolog/programa/programa/pinnaspis.jpg',' ICERVA PUTCHASI').


%% En caso de que las selecciones sean estas es por que es un polilla
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10) :-
        espregunta('presenta larvas blancas?',P1),P1='no',
        espregunta('la larva es grande?',P2),P2='no',
        espregunta('tiene las hojas comidas?',P3),P3='no',
        espregunta('tiene la medula de tallo destruida ?',P4),P4='si',
        espregunta('la larva es alargada?',P5),P5='no',
        espregunta('se encuentra en las vainas?',P6),P6='no',
        espregunta('se encuentra en las ramas?',P7),P7='no',
        espregunta('se encuentra  en los tallos?',P8),P8='no',
        espregunta('tiene brotes comidas?',P9),P9='no',
        espregunta('tiene el follaje destruida ?',P10),P10='si',
        pf1('C:/Users/cenb/Documents/Prolog/programa/programa/polilla.jpg','POLILLA','C:/Users/cenb/Documents/Prolog/programa/programa/polillast.jpg').

%% En caso de que las selecciones sean estas es por que es un larvas de polilla
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10) :-
        espregunta('presenta larvas blancas?',P1),P1='no',
        espregunta('la larva es grande?',P2),P2='no',
        espregunta('tiene las hojas comidas?',P3),P3='si',
        espregunta('tiene la medula de tallo destruida ?',P4),P4='no',
        espregunta('la larva es alargada?',P5),P5='no',
        espregunta('se encuentra en las vainas?',P6),P6='no',
        espregunta('se encuentra en las ramas?',P7),P7='no',
        espregunta('se encuentra  en los tallos?',P8),P8='no',
        espregunta('tiene brotes comidas?',P9),P9='si',
        espregunta('tiene el follaje destruida ?',P10),P10='no',
        pf1('C:/Users/cenb/Documents/Prolog/programa/programa/larvasp.jpg','LARVAS DE POLILLA','C:/Users/cenb/Documents/Prolog/programa/programa/polillast.jpg').

%% En caso de que no entre a ninguna de las anteriores no puede determinar la plaga
larvas(_,_,_,_,_,_,_,_,_,_) :-
        new(D,dialog('ERROR')),
        new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
        send(D,append,L),
        send(D,open,point(350,350)).

image(X) :-new(D,dialog('PLAGA')), %% Crea una nueva ventana
        mostrar1(X,D), %% Llama a mostrar 1 pasandole la ventana y la ruta de la imagen
        %new(B,label(salir,message(D,destroy))),
        %send(D,append,B),
        send(D,open). %% Abre la ventana

%% Despliega dos imagenes X y Z en la misma ventana con el nombre Y (Para insectos)
pf1(X,Y,Z) :-
        new(D,dialog('IMAGEN DE LA PLAGA')), %% Crea la ventana
        %% Despliega X
        mostrar(X,D,20,30),
        send(D, append(label(n,'INSECTOS'))),
        send(D, append(label(n,Y))), %% Agrega el nombre Y
        %% Despliega Z
        mostrar(Z,D,20,350),
        send(D,open).

%% Despliega dos imagenes X y Z en la misma ventana con el nombre Y (Para acaros)
pf2(X,Y,Z) :-
        new(D,dialog('IMAGEN DE LA PLAGA')), %% Crea la ventana
        %% Despliega X
        mostrar(X,D,20,40),
        new(L,label(n,'TRATAMIENTO')),
        send(D, append(label(n,'ACARO'))),
        send(D, append(label(n,Y))), %% Agrega el nombre Y
        send(D,append,L),
        %% Despliega Z
        mostrar(Z,D,20,350),
        send(D,open).

%% Despliega dos imagenes X y Z en la misma ventana con el nombre Y (Para acaros)
pf3(X,Y,Z) :-
        new(D,dialog('IMAGEN DE LA PLAGA')), %% Crea la ventana
        %% Despliega X
        mostrar(X,D,20,30),
        new(L,label(n,'TRATAMIENTO')),
        send(D, append(label(n,'INSECTO'))),
        send(D, append(label(n,'QUEREZA'))),
        send(D, append(label(n,Y))),%% Agrega el nombre Y
        send(D,append,L),
        %% Despliega Z
        mostrar(Z,D,20,350),
        send(D,open).

%% Sentencia para desplegar una imagen en una ventana
mostrar(V,D) :-
        new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1).

%% Sentencia para desplegar una imagen debajo de un componente
mostrar(V,D,M) :- new(I, image(V)), %% Carga la imagen pasada como parametro
        new(B, bitmap(I)), %% Genera el mapa de bits de la imagen
        new(F2, figure), %% Crea una figura
        send(F2, display, B), %% Despliega el mapa de bits
        new(D1, device), %% Crea un dispositivo
        send(D1, display, F2), %% Despliega la figura
        send(D, display, D1), %% Despliega el dispositivo en la ventana D
        send(D1,below(M)). %% Coloca el dispositivo debajo del menu

%% Sentencia para desplegar una imagen en un punto especifico de la ventana
mostrar(V,D,X,Y) :-
        new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1),
        send(D,display,D1,point(X,Y)).




















