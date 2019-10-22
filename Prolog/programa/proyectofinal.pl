:- use_module(library(pce)).

esrespuesta('si').
esrespuesta('no').
%MOSCA BLANCA
espregunta('se ubica en el enves de la hoja ?',X):-esrespuesta(X).
espregunta('tiene presencia de hojas picadas?',X):-esrespuesta(X).
espregunta('tiene presencia de hongos ?',X):-esrespuesta(X).
espregunta('tiene secreciones melosas ?',X):-esrespuesta(X).
%POLILLA
espregunta('tiene las hojas comidas?',X):-esrespuesta(X).
espregunta('tiene brotes comidas?',X):-esrespuesta(X).
espregunta('tiene la medula de tallo destruida ?',X):-esrespuesta(X).
espregunta('tiene el follaje destruida ?',X):-esrespuesta(X).
%PULGONES
espregunta('presenta las yemas caidas ?',X):-esrespuesta(X).
espregunta('presenta frutos pequeños?',X):-esrespuesta(X).
espregunta('presenta brotes muertos',X):-esrespuesta(X).
espregunta('son brotes pequeños?',X):-esrespuesta(X).
espregunta('presenta huevecillos?',X):-esrespuesta(X).
espregunta('baja produccion de vainas?',X):-esrespuesta(X).
espregunta('tiene las vainas encurvadas ?',X):-esrespuesta(X).
espregunta('tiene las hojas encrespadas?',X):-esrespuesta(X).
espregunta('vainas con peso normal?',X):-esrespuesta(X).
espregunta('vainas con tamaño normal?',X):-esrespuesta(X).
espregunta('presenta ceniza blanca en hojas?',X):-esrespuesta(X).
espregunta('enegrecimiento de brotes?',X):-esrespuesta(X).
espregunta('enegrecimiento de flores?',X):-esrespuesta(X).
espregunta('enegrecimiento de frutos?',X):-esrespuesta(X).
%ACARINA
espregunta('tiene manchas blanquesinas ?',X):-esrespuesta(X).
espregunta('tiene las hojas secas ?',X):-esrespuesta(X).
espregunta('tiene perdida de hojas?',X):-esrespuesta(X).
%CHINCHES
espregunta('tiene las hojas picadas?',X):-esrespuesta(X).
espregunta('tiene las hojas encojidas?',X):-esrespuesta(X).
%MOSCA MINADORA

espregunta('tiene las hojas minadas?',X):-esrespuesta(X).

%QUEREZA
espregunta('presenta larvas blancas?',X):-esrespuesta(X).
espregunta('la larva es alargada?',X):-esrespuesta(X).
espregunta('se encuentra en las vainas?',X):-esrespuesta(X).
espregunta('la larva es pequeña?',X):-esrespuesta(X).
espregunta('la larva es grande?',X):-esrespuesta(X).
espregunta('se encuentra en las ramas?',X):-esrespuesta(X).
espregunta('se encuentra  en los tallos?',X):-esrespuesta(X).
%PRINCIPAL
espregunta('es una larva ?',X):-esrespuesta(X).
espregunta('ataca hojas ?',X):-esrespuesta(X).
espregunta('presenta hongos ?',X):-esrespuesta(X).

main:-
        new(D,dialog('SISTEMA EXPERTO DE PLAGAS DE TARA')),
        send(D,size,size(560,400)),
        send(D,colour,colour(red)),
        send(D, append, new(Menu, menu_bar)),
        send(Menu, append, new(Iniciar, popup(iniciar))),
        send(Menu, append, new(Ayuda, popup(ayuda))),
        send_list(Iniciar, append,
                         [ menu_item(iniciar, message(@prolog,pp))
                         ]),
        send_list(Ayuda, append,
                         [ menu_item(autor, message(@display, inform, 'rolandopalermo.blogspot.com'))
                         ]),
        mostrar('C:/Programa/talla.bmp',D,Menu),
        send(D,open,point(200,200)).


mostrar(V,D,M):- new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1),
        send(D1,below(M)).

pp:-new(D,dialog('PREGUNTAS')),
        new(Pre1,menu('es una larva ?')),
       send_list(Pre1,append,[si , no]),
        new(Pre2,menu('ataca hojas ?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('presenta hongos ?')),
        send_list(Pre3,append,[si,no]),
          send(D,append(Pre1)),
       send(D,append,Pre2),
       send(D,append,Pre3),

      new(B,button(siguiente,and(message(@prolog,principal,Pre1?selection,Pre2?selection,Pre3?selection),message(D,destroy)))),
      send(D,append,B),
      send(D,default_button,siguiente),
        send(D,open,point(350,350)).



%PARA LARVAS
principal(P1,P2,P3):-
espregunta('es una larva ?',P1),P1='si',
espregunta('ataca hojas ?',P2),P2='no',
espregunta('presenta hongos ?',P3),P3='no',
pl.

principal(P1,P2,P3):-
espregunta('es una larva ?',P1),P1='no',
espregunta('ataca hojas ?',P2),P2='si',
espregunta('presenta hongos ?',P3),P3='no',
ph.

principal(P1,P2,P3):-
espregunta('es una larva ?',P1),P1='no',
espregunta('ataca hojas ?',P2),P2='no',
espregunta('presenta hongos ?',P3),P3='si',
pho.

principal(_,_,_):-new(D,dialog('ERROR')),
new(L,label(l,'ELIJA SOLO UNA OPCION',font('times','roman',16))),
send(D,append,L),
send(D,open,point(350,350)).

pho:-new(D,dialog('PREGUNTAS')),
        new(Pre1,menu('vainas con peso normal?')),
       send_list(Pre1,append,[si , no]),
        new(Pre2,menu('enegrecimiento de brotes?')),
        send_list(Pre2,append,[si,no]),
        new(Pre3,menu('vainas con tamaño normal?')),
        send_list(Pre3,append,[si,no]),
        new(Pre4,menu('presenta ceniza blanca en hojas?')),
        send_list(Pre4,append,[si,no]),
        new(Pre5,menu('enegrecimiento de flores?')),
       send_list(Pre5,append,[si,no]),
        new(Pre6,menu('enegrecimiento de frutos?')),
         send_list(Pre6,append,[si,no]),
       send(D,append(Pre1)),
       send(D,append,Pre2),
       send(D,append,Pre3),
       send(D,append,Pre4),
       send(D,append,Pre5),
       send(D,append(Pre6)),
       new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
                new(B,button(siguiente,message(@prolog,hongos,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection))),
                 send(D,append,B1),
                send(D,append,B),

        send(D,open,point(300,300)).

hongos(P1,P2,P3,P4,P5,P6):-
espregunta('vainas con peso normal?',P1),P1='si',
espregunta('enegrecimiento de brotes?',P2),P2='no',
espregunta('vainas con tamaño normal?',P3),P3='si',
espregunta('presenta ceniza blanca en hojas?',P4),P4='si',
espregunta('enegrecimiento de flores?',P5),P5='no',
espregunta('enegrecimiento de frutos?',P6),P6='no',

  ppm.

hongos(P1,P2,P3,P4,P5,P6):-
espregunta('vainas con peso normal?',P1),P1='no',
espregunta('enegrecimiento de brotes?',P2),P2='si',
espregunta('vainas con tamaño normal?',P3),P3='no',
espregunta('presenta ceniza blanca en hojas?',P4),P4='no',
espregunta('enegrecimiento de flores?',P5),P5='si',
espregunta('enegrecimiento de frutos?',P6),P6='si',

   ppm.
hongos(_,_,_,_,_,_):-new(D,dialog('ERROR')),
new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
send(D,append,L),
send(D,open,point(350,350)).

%MOSCAS O PULGONES
ppm:-new(D,dialog('PREGUNTAS')),
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

          send(D,append(Pre1)),
       send(D,append,Pre2),
       send(D,append,Pre3),
       send(D,append,Pre4),
       send(D,append,Pre5),
             send(D,append(Pre6)),
       send(D,append,Pre7),
       send(D,append,Pre8),


      new(B1,button(atras,and(message(@prolog,pho),message(D,destroy)))),
      new(B,button(siguiente,message(@prolog,pulmos,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection,Pre7?selection,Pre8?selection))),
      send(D,append,B1),
      send(D,append,B),
        send(D,open).
pulmos(P1,P2,P3,P4,P5,P6,P7,P8):-
        espregunta('tiene las hojas encrespadas?',P1),P1='si',


espregunta('tiene las vainas encurvadas ?',P2),P2='si',

espregunta('tiene presencia de hojas picadas?',P3),P3='no',
espregunta('baja produccion de vainas?',P4),P4='si',
espregunta('se ubica en el enves de la hoja ?',P5),P5='no',
espregunta('tiene secreciones melosas ?',P6),P6='si',
espregunta('presenta huevecillos?',P7),P7='si',
espregunta('presenta las yemas caidas ?',P8),P8='si',

        pf2('C:/Programa/pulgones.jpg','PULGONES','C:/Programa/pulgonest.jpg').
pulmos(P1,P2,P3,P4,P5,P6,P7,P8):-
espregunta('tiene las hojas encrespadas?',P1),P1='no',
espregunta('tiene las vainas encurvadas ?',P2),P2='no',
espregunta('tiene presencia de hojas picadas?',P3),P3='si',
espregunta('baja produccion de vainas?',P4),P4='no',
espregunta('se ubica en el enves de la hoja ?',P5),P5='si',
espregunta('tiene secreciones melosas ?',P6),P6='si',
espregunta('presenta huevecillos?',P7),P7='no',
espregunta('presenta las yemas caidas ?',P8),P8='no',
pf2('C:/Programa/moscab.jpg','MOSCA BLANCA','C:/Programa/moscabt.jpg').

pulmos(_,_,_,_,_,_,_,_):-new(D,dialog('ERROR')),
new(L,label(l,'ELIJA SOLO UNA OPCION',font('times','roman',16))),
send(D,append,L),
send(D,open,point(300,300)).
%PANTALLA DE HOJAS
ph:-new(D,dialog('PREGUNTAS')),
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
       send(D,append(Pre1)),
       send(D,append,Pre2),
       send(D,append,Pre3),
       send(D,append,Pre4),
       send(D,append,Pre5),
       send(D,append(Pre6)),
       new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
                new(B,button(siguiente,message(@prolog,hojas,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection))),
        send(D,append,B1),
      send(D,append,B),
        send(D,open,point(300,300)).


%CHINCHES
hojas(P1,P2,P3,P4,P5,P6):-
espregunta('tiene las hojas picadas?',P1),P1='si',
espregunta('tiene manchas blanquesinas ?',P2),P2='no',
espregunta('tiene las hojas minadas?',P3),P3='no',
espregunta('tiene las hojas encojidas?',P4),P4='si',
espregunta('tiene las hojas secas ?',P5),P5='no',
espregunta('tiene perdida de hojas?',P6),P6='no',

        pf1('C:/Programa/chinches.jpg','CHINCHES','C:/Programa/miguel.bmp').
%Mosca minadora terminado
hojas(P1,P2,P3,P4,P5,P6):-
espregunta('tiene las hojas picadas?',P1),P1='no',
espregunta('tiene manchas blanquesinas ?',P2),P2='no',
espregunta('tiene las hojas minadas?',P3),P3='si',
espregunta('tiene las hojas encojidas?',P4),P4='no',
espregunta('tiene las hojas secas ?',P5),P5='no',
espregunta('tiene perdida de hojas?',P6),P6='no',

        pf1('C:/Programa/moscam.jpg','MOSCA MINADORA','C:/Programa/moscamt.bmp').

%ACARINA
hojas(P1,P2,P3,P4,P5,P6):-
espregunta('tiene las hojas picadas?',P1),P1='no',
espregunta('tiene manchas blanquesinas ?',P2),P2='si',
espregunta('tiene las hojas minadas?',P3),P3='no',
espregunta('tiene las hojas encojidas?',P4),P4='no',
espregunta('tiene las hojas secas ?',P5),P5='si',
espregunta('tiene perdida de hojas?',P6),P6='si',

        pf2('C:/Programa/acarina.jpg','ACARINA','C:/Programa/acarinat.bmp').

hojas(_,_,_,_,_,_):-new(D,dialog('ERROR')),
new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
send(D,append,L),
send(D,open,point(350,350)).

pl:-new(D,dialog('PREGUNTAS')),
        new(Pre1,menu('presenta   larvas   blancas?')),
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

       new(B1,button(atras,and(message(@prolog,pp),message(D,destroy)))),
      new(B,button(siguiente,message(@prolog,larvas,Pre1?selection,Pre2?selection,Pre3?selection,Pre4?selection,Pre5?selection,Pre6?selection,Pre7?selection,Pre8?selection,Pre9?selection,Pre10?selection))),
      send(D,append,B1),
      send(D,append,B),
        send(D,open,point(300,300)).







%PINNASPIS

larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10):-
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
pf3('C:/Programa/pinnaspis.jpg','PINNASPIS','C:/Programa/pinnaspist.bmp').
%ICERVA PURCHASI
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10):-
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
        pf3('C:/Programa/pinnaspis.jpg',' ICERVA PUTCHASI').


%LARVAS DE POLILLAS
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10):-
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

pf1('C:/Programa/polilla.jpg','POLILLA','C:/Programa/polillast.jpg').
        %BARRENEDORES
larvas(P1,P2,P3,P4,P5,P6,P7,P8,P9,P10):-
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

 pf1('C:/Programa/larvasp.jpg','LARVAS DE POLILLA','C:/Programa/polillast.jpg').





larvas(_,_,_,_,_,_,_,_,_,_):-new(D,dialog('ERROR')),
new(L,label(l,'PLAGA NO DETERMINADA',font('times','roman',16))),
send(D,append,L),
send(D,open,point(350,350)).









image(X):-new(D,dialog('PLAGA')),
        mostrar1(X,D),
%       new(B,label(salir,message(D,destroy))),
        %send(D,append,B),
        send(D,open).




mostrar1(V,D):- new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1).



pf1(X,Y,Z):-new(D,dialog('IMAGEN DE LA PLAGA')),
          mostrar2(X,D,20,30),
         send(D, append(label(n,'INSECTOS'))),
         send(D, append(label(n,Y))),
         mostrar2(Z,D,20,350),
         send(D,open).

f1(X,Y):-new(F,frame('IMAGEN DE LA PLAGA')),
  send(F,append(new(F1,browser))),
   mostrar(X,F1),
   send(new(D,dialog),below(F1)),
   send(D, append(label(n,'INSECTOS'))),
   send(D, append(label(n,Y))),


   send(F,open,point(300,300)).


pf2(X,Y,Z):-new(D,dialog('IMAGEN DE LA PLAGA')),
          mostrar2(X,D,20,40),
          new(L,label(n,'TRATAMIENTO')),
         send(D, append(label(n,'ACARO'))),
         send(D, append(label(n,Y))),
         send(D,append,L),
          mostrar2(Z,D,20,350),
         send(D,open).

f2(X,Y):-new(F,frame('IMAGEN DE LA PLAGA')),
  send(F,append(new(F1,browser))),
   mostrar(X,F1),
   send(new(D,dialog),below(F1)),
   send(D, append(label(n,'ACARO'))),
   send(D, append(label(n,Y))),

   send(F,open,point(300,300)).

pf3(X,Y,Z):-new(D,dialog('IMAGEN DE LA PLAGA')),
          mostrar2(X,D,20,30),
           new(L,label(n,'TRATAMIENTO')),
          send(D, append(label(n,'INSECTO'))),
    send(D, append(label(n,'QUEREZA'))),
         send(D, append(label(n,Y))),
          send(D,append,L),
           mostrar2(Z,D,20,350),
         send(D,open).

f3(X,Y):-new(F,frame('IMAGEN DE LA PLAGA')),
  send(F,append(new(F1,browser))),
   mostrar(X,F1),
   send(new(D,dialog),below(F1)),
   send(D, append(label(n,'INSECTO'))),
    send(D, append(label(n,'QUEREZA'))),
   send(D, append(label(n,Y))),

   send(F,open,point(300,300)).

mostrar(V,D):- new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1).

mostrar2(V,D,X,Y):- new(I, image(V)),
        new(B, bitmap(I)),
        new(F2, figure),
        send(F2, display, B),
        new(D1, device),
        send(D1, display, F2),
        send(D, display, D1),
        send(D,display,D1,point(X,Y)).




















