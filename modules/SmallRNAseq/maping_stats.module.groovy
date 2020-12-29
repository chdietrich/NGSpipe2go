MappingStatsPlot = {
	doc title: "Statistics of mapping efficiency",
		desc:  "Counts the number of reads in the mapped bam, including total, unique, and mapped. Returns a plot of the results.",
      constraints: "Bam files produced by Bowtie 1.x. Might not work for other mappers/versions.",
		author: "Antonio Domingues"

   MAPPING_STATS_TOOL = MAPPING_STATS_TOOL_PATH + "/mapping_stats_bowtie1.R"

	produce(
        MAPPING_STATS_OUTDIR + "/totalReads.pdf",
        MAPPING_STATS_OUTDIR + "/totalReads.png") {

      exec """
         module load R/${R_VERSION} &&

         cd ${MAPPING_STATS_OUTDIR} &&
		 Rscript ${MAPPING_STATS_TOOL}

		""","MappingStatsPlot"
	}
}
