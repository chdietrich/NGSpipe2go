PIPELINE="tenXmultiome"
PIPELINE_VERSION="1.0"
PIPELINE_ROOT="./NGSpipe2go/"    // may need adjustment for some projects

load PIPELINE_ROOT + "/pipelines/scRNAseq/essential.vars.groovy"
load PIPELINE_ROOT + "/pipelines/scRNAseq/tools.groovy"
load PIPELINE_ROOT + "/config/preambles.groovy"
load PIPELINE_ROOT + "/config/bpipe.config.groovy"

load PIPELINE_ROOT + "/modules/scRNAseq/cellrangerarc_count.header"
load PIPELINE_ROOT + "/modules/scRNAseq/cellrangerarc_aggr.header"
load PIPELINE_ROOT + "/modules/NGS/bamcoverage.header"
load PIPELINE_ROOT + "/modules/NGS/bamindexer.header"
load PIPELINE_ROOT + "/modules/NGS/fastqc.header"
load PIPELINE_ROOT + "/modules/NGS/fastqscreen.header"
load PIPELINE_ROOT + "/modules/NGS/markdups2.header"
load PIPELINE_ROOT + "/modules/NGS/insertsize.header"
load PIPELINE_ROOT + "/modules/NGS/cutadapt.header"
load PIPELINE_ROOT + "/modules/RNAseq/dupradar.header"
load PIPELINE_ROOT + "/modules/RNAseq/genebodycov2.header"
load PIPELINE_ROOT + "/modules/RNAseq/inferexperiment.header"
load PIPELINE_ROOT + "/modules/RNAseq/qualimap.header"
load PIPELINE_ROOT + "/modules/RNAseq/star.header"
load PIPELINE_ROOT + "/modules/RNAseq/subread.header"
load PIPELINE_ROOT + "/modules/RNAseq/filter2htseq.header"
load PIPELINE_ROOT + "/modules/RNAseq/subread2rnatypes.header"
load PIPELINE_ROOT + "/modules/miscellaneous/collectbpipes.module.2.header"
load PIPELINE_ROOT + "/modules/miscellaneous/collect_tool_versions.header"
load PIPELINE_ROOT + "/modules/scRNAseq/shinyreports.header"
load PIPELINE_ROOT + "/modules/NGS/multiqc.header"


dontrun = { println "didn't run $module" }

Bpipe.run { 
    "%.fastq.gz" * [ FastQC + FastqScreen +
      (RUN_CUTADAPT ? Cutadapt + FastQC.using(subdir:"trimmed") : dontrun.using(module:"Cutadapt")) ] + 
      "%_gex_S*_L*_R*_001.fastq.gz" * [
         cellrangerarc_count + [
            bamCoverage,
            inferexperiment,
            qualimap,
            subread2rnatypes,
            dupRadar,
            geneBodyCov2
         ]
    ] + 
    cellrangerarc_aggr +
    (RUN_TRACKHUB ? trackhub_config + trackhub : dontrun.using(module:"trackhub")) +
    collectToolVersions + collectBpipeLogs + MultiQC + shinyReports
}
