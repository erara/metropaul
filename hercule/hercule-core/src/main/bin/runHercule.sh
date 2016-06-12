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
	echo "----------------------------------------------------------------------------"
    echo "	Usage: "
    echo "		$0 import"
    echo "		$0 export"
    echo "		$0 itineraires"
    echo "		$0 dump {user} {password}"
	echo "----------------------------------------------------------------------------"
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
if [ $# -eq 0 ]; then
	Usage
	exit $CODE_ERREUR_USAGE
elif [ "$1" = "import" ]||[ "$1" = "export" ]||[ "$1" = "itineraires" ]; then
	if [ $# -ne 1  ];  then
		echo "Les paramètres de la ligne de commande ne sont pas corrects."
		Usage
		exit $CODE_ERREUR_USAGE
	fi
elif [ "$1" = "dump" ]; then
	if [ $# -ne 3  ];  then
		echo "Les paramètres de la ligne de commande ne sont pas corrects."
		Usage
		exit $CODE_ERREUR_USAGE
	fi 
else
	Usage
	exit $CODE_ERREUR_USAGE
fi


# Répertoire d'exécution du batch
DIR=`echo $( cd -P -- "$(dirname -- "$(command -v -- "$0")")" && pwd -P )`


#==============================================
# Ajout des classes + config dans le CLASSPATH
#==============================================
if [ "$1" = "import" ]||[ "$1" = "export" ]||[ "$1" = "itineraires" ]; then
	for jar in `find $DIR/../lib -name "*.jar" -print`
	do
		CLASSPATH=$jar:$CLASSPATH
	done
	CLASSPATH=$DIR/../config:$CLASSPATH
	HERCULE_DATA=$HOME/data
	java -Xmx256m -Xms64m -Dfile.encoding=UTF-8 -DHERCULE_DATA=$HERCULE_DATA -classpath $CLASSPATH com.hercule.workflow.Workflow $*
else
	DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
	MY_DUMP = cat ${DIR}/../config/hercule.properties | grep ^DUMP_TABLES | awk -F= '{print $2}'
	mysqldump -u $2 --password=$3 hercule ${MY_DUMP} > dump_metropaul_bdd.sql
	
fi
