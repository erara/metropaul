#!/usr/bin/sh

#-----------------------------Header-----------------------------------
#
# Name :  runHercule.sh
#
# Description : Script qui lance le traitement d'import/export/update de la base de données
#
# Parameters :
#
#
#----------------------------------------------------------------------

# Definition des codes retour
CODE_RETOUR_OK=0
CODE_ERREUR_SYNTAXIQUE=1
CODE_ERREUR_TECHNIQUE=2
CODE_ERREUR_USAGE=3
CODE_ERREUR_CONFIG=4


Usage() {
    echo "\nUsage: "
    echo "$0 "
}


#==============================================
# Si on demande l'aide, Usage
#==============================================
if [ "$1" = "-h" ];  then
    Usage
    exit $CODE_ERREUR_USAGE
fi


#==============================================
# Vérification des paramètres du batch
#==============================================
if [ $# -ne 1  ];  then
    echo "Les paramètres de la ligne de commande ne sont pas corrects."
    Usage
    exit $CODE_ERREUR_USAGE
fi



# Répertoire d'exécution du batch
DIR=`echo $( cd -P -- "$(dirname -- "$(command -v -- "$0")")" && pwd -P )`


#==============================================
# Ajout des classes + config dans le CLASSPATH
#==============================================

for jar in `find $DIR/../lib -name "*.jar" -print`
do
    CLASSPATH=$jar:$CLASSPATH
done

CLASSPATH=$DIR/../config:$CLASSPATH



java -Xmx256m -Xms64m -Dfile.encoding=ISO-8859-1 -classpath $CLASSPATH com.hercule.workflow.Workflow $*