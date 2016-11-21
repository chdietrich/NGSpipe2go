//rule for task MarkDups from catalog NGS, version 1
//desc: Mark with/without removing duplicated reads from a bam file
MarkDups = {
	doc title: "MarkDups",
		desc:  "Call picard tools to mark with/without removing duplicated reads from a bam file",
		constraints: "Picard tools version >= 1.141",
		bpipe_version: "tested with bpipe 0.9.8.7",
		author: "Sergi Sayols"

	output.dir=MAPPED
	def JAVA_FLAGS  = "-Xmx" + MARKDUPS_MAXMEM + "m"
	def MARKDUPS_FLAGS  = "REMOVE_DUPLICATES=FALSE ASSUME_SORTED=TRUE"

	transform(".bam") to (".dupmarked.bam") {
		exec """
			export TOOL_DEPENDENCIES=$TOOL_DEPENDENCIES &&
            source ${TOOL_JAVA}/env.sh                  &&
            source ${TOOL_PICARD}/env.sh                &&
			
			echo 'VERSION INFO'  1>&2 ;
			echo \$(java -jar ${TOOL_PICARD}/picard.jar MarkDuplicates --version 2>&1 | cut -d'(' -f1 ) 1>&2 ;
			echo '/VERSION INFO' 1>&2 ;
			
			java $JAVA_FLAGS -jar ${TOOL_PICARD}/picard.jar MarkDuplicates $MARKDUPS_FLAGS INPUT=$input OUTPUT=$output METRICS_FILE=${input.prefix}_dupmetrics.tsv
		""","MarkDups"
	}
}

