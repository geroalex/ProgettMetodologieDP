PROGETTO DI METODOLOGIE DI PROGRAMMAZIONE:

Metodologie di Programmazione Modellazione e Gestione della Conoscenza Programmazione Avanzata Specifica Progetto Giugno/Luglio/Settembre 2025

Per poter pianificare al meglio il nostro budget familiare abbiamo bisogno di strumenti che consentano da un lato di tenere traccia delle spese sostenute e, allo stesso modo, ci permettano di pianificare al meglio i nostri progetti considerando quelle che saranno gli introiti previsti per il futuro. Questa necessità ha spinto il reparto sviluppo della nostra azienda a progettare il prototipo di una applicazione Java che consenta di gestire il budget familiare. Vi è stato assegnato quindi il compito di progettare tale applicazione.

In questa fase iniziale di progettazione non sono state ancora individuate con precisione le funzionalità che devono essere sviluppate. In una riunione preliminare, però, sono state elencate alcune delle caratteristiche che la applicazione che dovrà mettere:

● Gestione movimenti: l’applicazione deve consentire di inserire e consultare la lista dei movimenti effettuati. Sarà possibile associare ad ogni movimento uno (o più) tag che ne caratterizzano la tipologia (Sport, Utenze, Manutenzione auto,...). Le categorie possono essere organizzate in modo gerarchico. Potrebbe essere possibile inserire movimenti programmati per date specifiche. Ad esempio, potrebbe essere possibile caricare un piano di ammortamento di un prestito (definito come una sequenza di rate suddivise in quota rimborso e interessi). ● Gestione budget: dovrebbe consentire la possibilità di configurare e gestire il bilancio familiare indicando, indicando spese e ricavi per periodo di tempo e per tag/categoria. ● Scadenzario: l’applicazione consente di inserire spese future e relative scadenze. ● Statistiche: le informazioni inserite devono poter essere elaborate per fornire all’utente la possibilità di seguire l’andamento del proprio bilancio familiare consentendo, ad esempio, di poter effettuare un confronto tra diversi periodi di tempo e le spese sostenute nelle diverse categorie.

E’ importante chiarire che le specifiche caratteristiche dell’applicazione da sviluppare costituiscono una vostra scelta e parte della valutazione.

L’applicazione dovrà essere progettata in modo da poter garantire possibili future estensioni che ne garantiscano l’utilizzo su più dispositivi (applicazione desktop, mobile, web,...) e facilitare l’integrazione di nuove funzionalità. Non è importante quindi che tutte le funzionalità siano disponibili nella prima release, ma che sia chiaro come queste possano essere integrate in un futuro. Inoltre, deve essere prevista la possibilità di sincronizzare le informazioni tra diversi dispositivi.

L’applicazione sviluppata dovrà fornire un’interfaccia grafica per accedere alle funzionalià implemenate e meccanismi per la gestione della persistenza dei dati.

Tutte le classi sviluppate devono far parte del package:

it.unicam.cs.mpgc.jbudget

La consegna del progetto dovrà essere fatta attraverso caricando un archivio in formato .zip, .tgz o .tar.gz con una singola cartella contenente:

Un progetto Gradle per il building e l’esecuzione dell’applicazione;
Una relazione contenente una descrizione:
delle funzionalità implementate
delle responsabilità individuate
delle classi ed interfacce sviluppate con le responsabilità associate ad ognuna di queste;
dell’organizzazione dei dati e di come è stata garantita la persistenza
dei meccanismi messi a disposizione per poter integrare nuove funzionalità.
Nella valutazione del progetto verranno considerate: ● Definizione delle responsabilità delle classi ● Metodi coerenti con le responsabilità ● Principi SOLID rispettati ● Estendibilità del progetto ● Pulizia/Stile del codice ● Efficienza ● Uso degli strumenti e metodologie viste durante il corso ● Relazione (Scrittura)

CONSEGNE CHE NON RISPETTANO LA SPECIFICA NON VERRANNO VALUTATE!
