## Opis Projektu "Biblioteka"
### Cel Projektu
Projekt "Biblioteka" to aplikacja desktopowa stworzona w celu ułatwienia pracy pracowników bibliotek. Umożliwia obsługę użytkowników (osób wypożyczających) i zarządzanie zasobami bibliotecznymi. Aplikacja składa się z trzech głównych modułów: Osoby, Książki i Wypożyczenia, każdy z nich oferując funkcje takie jak edycja, usuwanie, dodawanie oraz filtrowanie.

### Technologie
- Java 19.0
- MySQL 8.0.31

### Baza Danych
Baza danych aplikacji składa się z 7 tabel, w tym tabel osób, książek, gatunków, autorów, wydawnictw oraz wypożyczeń. Tabele te są wzajemnie powiązane, umożliwiając kompleksowe zarządzanie danymi.
![image](https://github.com/Krzychulec21/LibraryApp/assets/101758475/80827d38-d081-491f-9625-8702cd76e18f)


### Instalacja
Aplikacja jest dostępna jako plik wykonywalny (.exe), którego instalacja odbywa się za pomocą standardowego instalatora. Wymagana jest Java w wersji co najmniej 19.0. Konfiguracja połączenia z bazą danych odbywa się poprzez plik `hibernate.cfg.xml` (w pliku umieszczone są przykładowe dane do połączenia).

### Interfejs Użytkownika
Po uruchomieniu aplikacji użytkownikowi prezentowane jest okno logowania, z opcją rejestracji nowego konta. Po zalogowaniu dostępne są trzy główne opcje: Użytkownicy, Książki i Wypożyczenia, każda z nich prowadząca do dedykowanego interfejsu zarządzania.
![image](https://github.com/Krzychulec21/LibraryApp/assets/101758475/8543b6d4-1dd7-4b52-9889-00926064b481)
![image](https://github.com/Krzychulec21/LibraryApp/assets/101758475/380a31b4-197f-4ced-becc-aed3ce9b6bde)


### Bezpieczeństwo
Baza danych jest zabezpieczona hasłem. Dostęp do aplikacji chroniony jest przez okno logowania i specjalny kod rejestracji. Dodatkowo, w aplikacji zaimplementowane są zabezpieczenia przed niepoprawnym formatem wprowadzonych danych.

