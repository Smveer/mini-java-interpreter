# mini-java-interpreter
### Filière intelligence artificiel et big data - 3ème année ESGI

On souhaite coder un petit interpréteur de code, dont le langage ne manipule que des variables entières. Voici un exemple de code :

    input n
    incr n
    print n
	input a
	print a

Ce petit code contient 3 instructions, avec toujours une instruction et ses arguments par ligne.
- La première instruction réalise une saisie entière et stocke le résultat dans une variable n.
- La deuxième instruction incrémente la variable n (n++)
- La troisième instruction affiche le contenu de la variable n sur la sortie standard

Comment ça se passe du coté du code ? On se propose de construire d'abord les éléments suivants :
- Une classe abstraite Instruction munie d'une méthode run
- Une classe Program qui contient ou hérite d'une liste d'Instruction
- Une interface Context qui propose deux méthodes :
  -     setVariable(String name, Integer value)
        Integer getVariable(String name)

Les méthodes `Program.run()` et `Instruction.run(Context)` exécutent respectivement tout un programme ou une instruction.

Naturellement, la classe Program peut implémenter l'interface Context et ses méthodes.

Le jeu d'instruction est très réduit pour vous simplifier la vie :

    input [var]
        Affiche un prompt simple ">" sur la console  pour récupérer une valeur saisie par l'utilisateur. La valeur est stockée dans [var]

	set [var] [value]
        Affecte la valeur [value] à la variable [var]
        Exemple : set a 2 (comme en java int a = 2;)
		
	incr [var]
        Incrément la variable [var]
		
	print [var]
        Afficher le contenu de la variable [var]

Chaque instruction peut hériter de la classe abstraite Instruction.

Dans la classe Program, on implémente la méthode `load(File)` qui permet de charger le programme (prévoir éventuellement une délégation d'exception)

Il y a donc fort à parier que la méthode `Program.load` mette en oeuvre un design pattern de type "Factory", ce qui permettra d'écrire une méthode `Program.run` très simple :

    public void run() {
        for (Instruction inst : this) {
            inst.run(this); //ici on suppose que Program implémente Context
        }
    }

Si l'on exécute l'exemple de code donné plus haut, l'exécution devrait donner une saisie puis un affichage:

    >2
    3