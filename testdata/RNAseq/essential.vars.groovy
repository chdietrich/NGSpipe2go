// RNA-Seq ESSENTIAL VARIABLES

// Define essential variables here.
// Further module-specific variables can be adjusted in the corresponding ".header" files for each module.

// General parameters
ESSENTIAL_PROJECT="/project"
ESSENTIAL_THREADS=4             // number of threads for parallel tasks
ESSENTIAL_SAMPLE_PREFIX=""      // sample name prefix to be trimmed in the results and reports

// Mapping and annotation parameters
ESSENTIAL_STAR_REF="/fsimb/common/genomes/saccharomyces_cerevisiae/ensembl/R64/canonical/index/star/2.7.3a/"
ESSENTIAL_GENESGTF="/fsimb/common/genomes/saccharomyces_cerevisiae/ensembl/R64/canonical/annotation/Saccharomyces_cerevisiae.R64-1-1.86.gtf"
ESSENTIAL_GENESBED="/fsimb/common/genomes/saccharomyces_cerevisiae/ensembl/R64/canonical/annotation/Saccharomyces_cerevisiae.R64-1-1.86.bed"
ESSENTIAL_CHROMSIZES="/fsimb/common/genomes/saccharomyces_cerevisiae/ensembl/R64/canonical/index/star/2.7.3a/chrNameLength.txt"  // chromosome sizes file
ESSENTIAL_FEATURETYPE="gene_biotype" //gencode uses gene_type; ensemble uses gene_biotype
ESSENTIAL_PAIRED="no"           // single-end ("no") or paired-end ("yes") data
ESSENTIAL_READLENGTH=50         // read length - used for optimal mapping over splice-junctions
ESSENTIAL_STRANDED="reverse"    // library prep protocol strandness: no|yes|reverse
ESSENTIAL_ORG="yeast"           // UCSC organism name (human, mouse, fly, worm, yeast, zebrafish)
ESSENTIAL_DB="sacCer3"              // UCSC assembly version
ESSENTIAL_FILTER_CHR=""         // chromosomes to include in post-mapping analysis
ESSENTIAL_BAMCOVERAGE="--binSize 1 --skipNonCoveredRegions --normalizeUsing CPM"  // size of bins (in bp) and normalisation method for the bigWig tracks

// DESeq2 specific parameters
ESSENTIAL_DESEQ2_FDR=0.01       // FDR significance cutoff in the DESeq2 model (may be increased for noisy or underpowered datasets)
ESSENTIAL_DESEQ2_FC=1           // optional Fold-Change cutoff to incorporate into the DESeq2 model (default "FC=1" means no Fold-Change filter is used)
// Using FC threshold in the DESeq2 model is usually more conservative than post-hoc gene filtering by FC (which should anyway be avoided, see https://doi.org/10.1093/bib/bbab053) 

// Optional pipeline stages to include
RUN_TRACKHUB=false              // prepare a Track Hub for the UCSC genome browser
RUN_FASTQSCREEN=true            // check for contaminations using FastQ Screen
RUN_CUTADAPT=false              // optional read trimming with Cutadapt e.g. if using high read length
RUN_IN_PAIRED_END_MODE=(ESSENTIAL_PAIRED == "yes") // no need to change this line
RUN_RMATS=true // check for differential splicing events using the provided contrasts; mmatrix of contrasts file will be ignored
// In a typical setup, batch effect correction is not necessary for rMATS, as it calculates a ratio of splicing events with in each sample separately and then compares it with the respective replicates. Before comparing with replicates, they also model the variation among the replicates by random effects in a mixed model. In general, the variation accounting technical differences among replicates seems to be taken care by it. 

// FASTQ-Screen parameters
ESSENTIAL_FASTQSCREEN_PERC=1    // contaminant filter, if a contaminant is consuming at least this percentage of reads in at least one sample, contaminant will be shown in report
ESSENTIAL_FASTQSCREEN_GENOME="Yeast::/fsimb/common/genomes/saccharomyces_cerevisiae/ensembl/R64/canonical/index/bowtie2/2.3.2/Saccharomyces_cerevisiae.R64-1-1.dna.toplevel"  //bowtie2 reference for the genome the samples are from, this is used for the fastqscreen
ESSENTIAL_FASTQSCREEN=ESSENTIAL_FASTQSCREEN_GENOME + ",PHIX::/fsimb/common/genomes/phix/19930428/NCBI/index/bowtie2/2.3.4.3/ncbi_phix,ERCC::/fsimb/common/genomes/ERCC/index/bowtie2/2.3.4.3/ERCC92,rRNA::/fsimb/common/genomes/contaminants/fastqscreen_references/rrna/v1/index/bowtie2/2.3.4.3/hs_mm_ce_dm_rn_dr_xt_rRNA,Mycoplasma::/fsimb/common/genomes/contaminants/fastqscreen_references/mycoplasma/v1/index/bowtie2/2.3.4.3/mycoplasma_all_ref,E.coli::/fsimb/common/genomes/Escherichia_coli/ensembl/full/index/bowtie2/Escherichia_coli_str_k_12_substr_dh10b.ASM1942v1.31.dna.genome,B.taurus::/fsimb/common/genomes/bos_taurus/ensembl/3.1/full/index/bowtie2/2.2.9/UMD3.1" //references for fastqscreen to use if run, this are our standard references please include yours 

// Adapter trimming with Cutadapt (additional adapter sequences for R1 and/or R2 can be specified in the cutadapt.header file)
// Cutadapt recommends using full length adapter sequences since adapter fragments might occur in the genome
ESSENTIAL_ADAPTER_SEQUENCE="Nextera=CTGTCTCTTATACACATCT" // 3'adapter sequence for R1. 
ESSENTIAL_MINADAPTEROVERLAP=5
ESSENTIAL_MINREADLENGTH=30
ESSENTIAL_BASEQUALCUTOFF=20  // trim low-quality ends from reads (if nextseqtrim is true, qualities of terminal G bases are ignored)  
ESSENTIAL_NEXTSEQTRIM=false   // accounts for terminal G bases during base quality trimming incorporated by faulty dark cycles observed with two-color chemistry (as in NextSeq) 


//project folders
PROJECT=ESSENTIAL_PROJECT
LOGS=PROJECT + "/logs"
MAPPED=PROJECT + "/mapped"
TRIMMED=PROJECT + "/trimmed"
QC=PROJECT + "/qc"
REPORTS=PROJECT + "/reports"
RESULTS=PROJECT + "/results"
TMP=PROJECT + "/tmp"
TRACKS=PROJECT + "/tracks"
FUSION=PROJECT + "/fusion"
