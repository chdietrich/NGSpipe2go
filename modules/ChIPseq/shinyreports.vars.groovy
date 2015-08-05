//vars for task shinyReports from catalog ChIPseq, version 1
SHINYREPS_PROJECT=PROJECT	//project directory
SHINYREPS_LOG=LOGS			//where the logs lie
SHINYREPS_QC=QC				//where the QC lie
SHINYREPS_RES=RESULTS		//where the results lie
SHINYREPS_PREFIX="Sample_imb_[A-Za-z]+_\\\\\\\\d+_\\\\\\\\d+_"  //standard sample prefix
SHINYREPS_TARGETS="targets.txt"
SHINYREPS_BOWTIE_LOG=LOGS + "/bowtie_se"	//where the STAR logs lie
SHINYREPS_MARKDUPS_LOG=LOGS + "/MarkDups"	//where the MarkDups logs lie
SHINYREPS_FASTQC=FASTQC_OUTDIR		        //where the Fastqc logs lie
SHINYREPS_IPSTRENGTH=QC + "/ipstrength"	//where the IPstrength files lie
SHINYREPS_PBC=QC + "/pbc"               	//where the PBC files lie
SHINYREPS_PHANTOMPEAK=QC + "/phantompeak"	//where the PhantomPeak files lie
SHINYREPS_BUSTARD=QC + "/DemultiplexedBustardSummary.xml"	//where the bustard xml file lies
SHINYREPS_MACS2=RESULTS + "/macs2"	//where the MACS2 results lie

