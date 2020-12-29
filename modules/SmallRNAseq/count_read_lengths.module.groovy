CountReadLengths = {
    doc title: "CountReadLengths",
    desc: "Determines the sequence length distribution in a fastq file",
    constraints: "Gzipped fastq",
    author: "António Domingues"

    output.dir = READ_LENGTH_DIR
    // keep only the file name without any extensions.
    def SAMPLE_NAME = input.split("/")[-1].split("\\.")[0]
   
    produce(SAMPLE_NAME + ".readlength.txt")  {
        exec """
            if [ -n "\$LSB_JOBID" ]; then
                export TMPDIR=/jobdir/\${LSB_JOBID};
            fi &&

            zcat $input | awk '{if(NR%4==2) print length(\$1)}' | sort -n | uniq -c > $output
        ""","CountReadLengths"
   }
   forward input
}
