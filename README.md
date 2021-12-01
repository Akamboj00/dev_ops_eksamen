# dev_ops_eksamen

<h1>Oppgave - DevOps</h1>
<h6>Med DevOps som arbeidsmåte i tankene- Hvilke forbedringer kan teamet gjøre med fokus på
måten de jobber med kildekode og versjonskontroll?
</h6>(De tre punktene gitt i drøftingsoppgaven svarer på dette spørsmålet).
<h6>
Beskriv med ord eller skjermbilder hvordan man kan konfigurere GitHub på en måte som
gir bedre kontroll på utviklingsprosessen. Spesielt med tanke på å hindre kode som ikke
kompilerer og feilende tester fra å bli integrert i main branch.
</h6>
<br>
En god metode å konfigurere dette på er å bruke GitHub actions. GitHub actions er et verktøy som eliminerer muligheten for å pushe feil kode og feilende tester til main branch. Dette verktøyet er viktig for å sikre at kvaliteten på koden opprettholdes, og at man ikke skaper noen konflikter med main branch ved å pushe kode som ikke fungerer. 
Oppsettet av dette er veldig enkelt. Man er nødt til å definere en arbeidsflyt(workflow). Måten du gjør det på er:

opprett en .yaml fil i denne eksakte pathen:
eksrepo / .github / workflows / npm.yaml

Det er viktig at filen opprettes på akkurat denne måten, med denne fil stien. Men, navnet på .yaml filen kan være valgfritt. Hvis ikke filen blir opprettet på denne måten klarer ikke GitHub å plukke opp arbeidsflyten, og det vil ikke fungere.
I yaml filen trenger du dermed litt kode som forteller github hva du ønsker å gjøre. For eksempel:

![](img/1.png)
<br>
Dette er et eksempel på hvordan man konfigurer en veldig enkel sjekk for et Javascript Prosjekt. I dette eksempelet forteller jeg github at på hver eneste PUSH, ønsker jeg å gjøre følgende:

I korte trekk, så sjekker dette om koden kompileres og om testene kjører som de skal. Hvis dette ikke er tilfellet, vil ikke koden bli pushet, og du får et varsel i “actions” fanen på repository. For eksempel:

![](img/2.png)

Du kan dermed rette opp i feilen, forsøke å pushe på nytt. Hvis alt fungerer vil du få dette varselet:

![](img/3.png)

I tillegg til dette så må man sette noen branch protection rules. Naviger til “settings” fanen, og trykk på “branches”. Under branches må du krysse av på følgende:

![](img/4.png)

I dette eksempelet blir det gjort status sjekk på koden mot Node JS v12 og v14.

Når koden er validert og alle sjekkene er fullført med suksess, så vil koden bli pushet.


<h6>Beskriv med ord eller skjermbilder hvordan GitHub kan konfigureres for å sikre at minst ett
annet medlem av teamet har godkjent en pull request før den merges.
</h6>

Dette er også veldig lett å konfigurere. I GitHub under riktig repository, naviger til “settings” og “branches. Under branches må du krysse av på følgende:
![](img/5.png)

For denne regelen er det viktig å presisere at det kun gjelder main branch.
Når dette er krysset av, så er man først og fremst nødt til å opprette separat branch når du skal kode noe. Jeg kommer tilbake til hvorfor det er viktig i neste oppgave. Du krysser også av på “require approvals” som gjør at hver gang du oppretter en pull request mot main, med den nye koden. Så er noen andre i teamet nødt til å lese gjennom denne og godkjenne. Når den er godkjent så blir pull requesten utført.


<h6>Beskriv hvordan arbeidsflyten for hver enkelt utvikler bør være for å få en effektiv som
mulig utviklingsprosess, spesielt hvordan hver enkelt utvikler bør jobbe med Brancher i
Github hver gang han eller hun starter en ny oppgave.
</h6>

For å skape en mest mulig effektiv utviklingsprosess, så er det en del steg man kan ta. For det første, stegene jeg har forklart i oppgavene over er essensielle. Som forklart så vil disse stegene bidra til at koden er så feilfri som mulig. Et annet viktig steg som jeg ikke har vært inne på i detalj, er at utvikleren skal opprette en ny branch for hver implementasjon. På denne måten vil du kunne validere hver branch med github actions. For å kunne pushe denne koden til main branch, så må det opprettes en pull request. Hvis stegene ovenfor blir fulgt, så vil det bety at pull requesten blir sett over og godkjent av en annen utvikler på laget før den blir pushet til main. På denne måten vil man tidlig oppdage feil, og all kode som er på main branch har potensial til å fungere til enhver tid. Alle stegene forklart ovenfor er en del av kontinuerlig integrasjon i pipeline metoden.

<h5> Drøft</h5>
<h6>SkalBank har bestemt seg for å bruke DevOps som underliggende prinsipp for all
systemutvikling i banken. Er fordeling av oppgaver mellom API-teamet og "Team Dino"
problematisk med dette som utgangspunkt? Hvilke prinsipper er det som ikke etterleves her?
Hva er i så fall konsekvensen av dette?
</h6>
Jeg vil si at fordelingen av oppgaver mellom teamene kan bli svært problematisk hvis de ønsker å bruke DevOps som underliggende prinsipp. Fundamentalt så følger ikke fordelingen CI/CD pipeline prinsippet. Det er mange utviklere som committer til main branch kontinuerlig, uten at koden blir validert. På denne måten er det veldig vanskelig å finne problemer og bugs med koden. Samtidig som at det ikke er noen garanti for at koden i main branch fungerer. Her kan det potensielt være mye konflikter.

Testing og deployment av koden foregår manuelt. Testingen skjer etter koden er pushet til main branch, og ikke før. Da er det ingen garanti for at kvaliteten på koden er god, og mye ekstra arbeid kan forekomme. Overvåkingen er også overfladisk. Denne metoden er generelt tidkrevende, ineffektiv, økonomisk belastende og eksponert for mange sikkerhetsproblemer.

Her kan det være lurt å starte helt på scratch og legge om strukturen slik at den er optimal for DevOps.

<h1>Oppgave Feedback</h1>
<h5>InfluxDB:</h5>

![](img/6.png)

<h5>Grafana dashbord:</h5>

![](img/7.png)

Når det gjelder denne oppgaven så har jeg noen mangler. I koden har jeg lagt til timer på alle endepunkter. Og denne metricen får man opp i influxDB. Jeg har også lagt til counter på backend exceptions, men denne metricen får jeg ikke opp i Influx eller grafana. Jeg lar koden stå i repository i håp om å få noe uttelling på denne oppgaven, men her er jeg klar over at det er en del mangler. For å få opp timer metric i Influx:

"select * from app_timer"


<h1>Oppgave Terraform</h1>
<h5>Drøft</h5>
<h6>
Hvorfor funket terraform koden i dette repoet for "Jens" første gang det ble kjørt? Og hvorfor
feiler det for alle andre etterpå, inkludert Jens etter at han ryddet på disken sin og slettet
terraform.tfstate filen?
</h6>

Først og fremst så inneholder infra/bucket.tf kode som oppretter en ny bucket. Så hvis teamet kjører denne koden flere ganger, så prøver den å opprette en bucket som allerede finnes. Det er ikke mulig. Det de heller burde gjøre, er å modifisere bucketen. Altså bruke data istedenfor resource. På den måten så har de en referanse til bucketen, og kan endre på den, feks legge til elementer. I tillegg til det, så slettet jens state filen. Da vet ikke terraform hvordan koden henger sammen og hvordan infrastrukturen henger sammen. I og med at jens gjorde det først, så vil det fungere første gangen. Men resten av teamet har ikke denne state filen, da koden prøver å opprette ny bucket hver gang, istedenfor å referere til bucketen. 

Opprett en S3 Bucket:

![](img/8.png)

<h6>Sensor ønsker å lage sin bucket ved hjelp av CLI. Sensor har aws kommandolinje installert på
sin lokale maskin. Hva må sensor gjøre for å konfigurere AWS nøkler/Credentials? Anta at
Sensor sin AWS bruker ikke har nøkler/credentials fra før.
</h6>

Først og fremst må sensor opprette en access key ID, og en secret access key. Det er veldig enkelt å gjøre i både i konsoll (ui) og i kommandolinje (cli):

Kommandolinje:

Skriv denne linjen i cli:


aws iam create-access-key --user-name “brukernavn”

I “brukernavn”, så må sensor skrive sitt IAM brukernavn.
Output:


{

"AccessKey": {

"UserName": "Brukernavn",

"Status": "Active",

"CreateDate": "xxxx-xx-xxTxx:xx:xx.xxxZ",

"SecretAccessKey": "wJalrXUtnFEMI/K7MDENG/bPxRfiCYzEXAMPLEKEY",

"AccessKeyId": "AKIAIOSFODNN7EXAMPLE"
    
   }
}


Console:

Trykk på knappen med IAM brukernavnet ditt øverst i høyre hjørne, og velg “security credentials”.

![](img/9.png)

Dermed navigerer du deg ned til Access keys for CLI, SDK, & API access, og trykker på knappen “create access key”. Da blir det generert en Access key ID og en Secret access key som du kan bruke videre.

![](img/10.png)

Etter du har opprettet access key på ønsket måte, må du kjøre kommando:

"aws configure"

Etter du har skrevet inn denne kommandoen, blir du bedt om å fylle inn Access Key ID, Secret access key, default region name og default output format.

Det fyller du ut på denne måten:

![](img/11.png)

Etter dette er fylt inn er alt klart, og du kan opprette en bucket. Det gjøres med en kommando:

"aws s3api create-bucket --bucket “navnPåBucket” --region eu-west-1 --create-bucket-configuration LocationConstraint=eu-west-1"

Output etter denne kommandoen:

}
"Location": "http://navnPåBucket.s3.amazonaws.com/"
}

Bucketen er nå opprettet og den kan finnes i AWS console, under Amazon S3/buckets.



<h1>Terraform i Pipeline</h1>
<h5>Sensor vil å lage en fork av ditt repo. 
 
 -Beskriv hva sensor må gjøre etter han/hun har laget en fork for å få pipeline til å fungere for i sin AWS/gitHub konto.
 -Hvilke verdier må endres i koden?
 -Hvilke hemmeligheter må legges inn i repoet. Hvordan gjøres dette?
</h5>

For å få pipeline til å fungere, så er det et par ting som må endres. Sensor må først og fremst legge til sine Access keys i github secrets. Det gjøres på følgende måte:
Settings/secrets/new repository secret
Når du har kommet dit, må du legge til to secrets. De må ha følgende navn og verdi:
AWS_ACCESS_KEY_ID - Denne skal inneholde sensor sin Access key id som vi opprettet tidligere i oppgaven.
AWS_SECRET_ACCESS_KEY - Denne skal inneholde sensor sin Secret Access Key, som vi opprettet tidligere i oppgaven.
Grunnen til at det legges til på denne måten er at Access keys forblir hemmelig. Så hvis noen ønsker å se på mitt repository, så har de ikke mulighet til å se mine nøkler hardkodet i koden. Ergo, det er ingen som får tilgang på min AWS konto. Dette er rett og slett et sikkerhetstiltak. Nøkler hardkodet inn i koden er regnet som “bad practice”.
Utover det; Sensor må endre på noen verdier i koden, slik at sensor kan bruke sin egen bucket.
i infra/provider.tf må sensor endre på to ting: Linje 9 må endres til navnet på bucketen som sensor opprettet tidligere i oppgaven. På linje 10, må sensor endre “abka007” med en annen unik key. Det kan være hva som helst, spiller ingen rolle hva som skrives der.

![](img/12.png)


Nå skal i teorien pipeline fungere. Men det er greit å endre på en siste ting slik at ting er ryddig og alt holdes på stell. I infra/ecrrepo.tf er det kode som oppretter et ecr repository i AWS med navn “2015abka007”.

![](img/13.png)

Her burde sensor velge et annet navn, slik at sensor får et unikt ECR repository, hvis det ønskes.

<h1>Oppgave Docker</h1>
<h5>Hva vil kommandolinje for å bygge et container image være? Fullfør …</h5>

"docker build . -t hello"

Denne kommandoen bygger imaget med Dockerfile, og gir den en referanse “hello”. Som vi kan bruke senere


<h5>Hva vil kommando for å starte en container være? Applikasjonen skal lytte på port 7777 på din maskin. Fullfør…</h5>

"docker run -p 7777:8080 hello:latest"

<h5>Medlemmer av "Team Dino" har av og til behov for å kjøre to ulike versjoner av applikasjonen lokalt på maskinen sin, samtidig .Hvordan kan de gjøre dette uten å få en port-konflikt? Hvilke to kommandoer kan man kjøre for å starte samme applikasjon to ganger, hvor den ene brukerport 7777 og den andre 8888?</h5>

"docker run --name hello1 -d -p 7777:8080 -p 8888:8080 hello:latest"

Med denne ene kommandoen kjører du på port 7777 og 8888.
