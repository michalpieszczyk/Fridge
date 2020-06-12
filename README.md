Projekt na zaliczenie przedmiotu - Języki Programowania Wysokiego poziomu

Android Studio – Java

YourFridge - Zarządzanie stanem produktów w twojej lodówce

Aplikacja zintegrowana z zapleczem google firebase. Proste UI, Funkcje włóż przedmiot do lodowki, zabierz przedmiot z lodówki, podejrzyj stan mojej lodówki. 

Klient (telefon) rozkodowuje kod kreskowy na cyfry I operuje na tych rekordach w bazie.

Zamysł:

Po zrobieniu zakupów skanujemy produkty przed włożeniem do lodówki. 

Funkcja dodaj produkty do lodówki - skanujemy kod kreskowy produktu, możemy dodać wiele sztuk danej pozycji lub skanowac produkty pojedynczo. 

Kod kreskowy sprawdzny jest z rekordami w bazie danych (rekord bedą dodane ręcznie, chyba że znajdę jakąś otwartą bazę produktów)

Funkcja zabierz produkt z lodówki. (zakładamy że użytkownik powinien brać produkt z najkrótszą datą ważności). Skanujemy kod, opcjonalnie podajemy ilość sztuk jaką zabieramy z lodówki. Baza aktualizuje stan lodówki w bazie (zmniejsza ilość dla najwcześniej dodanego rekordu z danym kodem kreskowym)

	• Opcjonalnie: wyświetlenie kalorii I makroskładników produktu. 

-----

Atrybuty tabeli produkt:
  kod_kreskowy	  nazwa	  gramatura	  kalorie	  bialko	  tluszcze  	  weglowodany	  dystrybutor	  ile_dni

Atrybuty tabeli lodowka:
data kod

		

