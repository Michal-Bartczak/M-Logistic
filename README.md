# M-Logistic - Aplikacja Zarządzania Paczkami

## Opis:
Aplikacja "Magazyn" została stworzona w celu zarządzania różnymi aspektami działalności magazynowej, w tym zarządzaniem klientami,
zamówieniami, dostawami i ich statusami oraz różnymi typami użytkowników.

## Funkcjonalności:
- **Zarządzanie klientami**: Możliwość dodawania, edycji i usuwania informacji o klientach.
- **Zarządzanie zamówieniami**: Śledzenie statusu zamówienia oraz jego historii.
- **Zarządzanie dostawami**: Monitorowanie statusu dostawy, w tym możliwość aktualizacji statusu oraz przeglądania logów dostaw.
- **Zarządzanie użytkownikami**: Obsługa różnych typów użytkowników, takich jak kierowcy, pracownicy i administratorzy.
- **Generowanie raportów**: Funkcjonalności związane z generowaniem różnych raportów, listów i etykiet przewozowych.
- **Międzynarodowa obsługa**: Aplikacja obsługuje różne języki, w tym angielski i polski.

## Użyte technologie:
- **Spring Framework**: W tym Spring Security do zarządzania zabezpieczeniami.
- **Jackson**: Obsługa danych w formacie JSON.
- **Logback i Log4j**: Narzędzia do logowania.
- **MySQL**: Baza danych.
- **JSTL**: JavaServer Pages Standard Tag Library.

## Struktura aplikacji:
Aplikacja podzielona jest na różne pakiety, takie jak:
- `controller`: Obsługuje żądania HTTP i zarządza przepływem danych w aplikacji.
- `service`: Zawiera logikę biznesową aplikacji.
- `entity`: Zawiera klasy encji, które reprezentują modele danych.
- `repository`: Interfejsy repozytoriów do wykonywania operacji na bazie danych.
- `dto`: Klasy Data Transfer Object do przekazywania danych między warstwami.
