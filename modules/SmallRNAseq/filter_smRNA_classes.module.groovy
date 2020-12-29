FilterRNAClasses = {
   doc title: "Filter small RNA classes",
         desc:  "Filter alignments to select C. elegans small RNA classes: 21U, 22G, 26G",
         constraints: "",
         author: "Antonio Domingues"

   output.dir = FILTER_CLASSES_OUTDIR

   transform(".bam") to (".21U.bam", ".22G.bam", ".26G.bam", ".miRNA.bam"){

      exec """
         module load bedtools/${BEDTOOLS_VERSION} &&
         module load htseq/${HTSEQ_VERSION} &&

         python $FILTER_CLASSES_TOOL_PATH -i $input -m 21 -M 21 -n T -p first -o stdout | intersectBed -a stdin -b $FILTER_CLASSES_21U_REF -s > $output1 &&
         python $FILTER_CLASSES_TOOL_PATH -i $input -m 20 -M 23 -n G -p first -o stdout | intersectBed -a stdin -b $FILTER_CLASSES_22G_REF -S > $output2 &&
         python $FILTER_CLASSES_TOOL_PATH -i $input -m 26 -M 26 -n G -p first -o stdout | intersectBed -a stdin -b $FILTER_CLASSES_26G_REF -S > $output3 &&
         python $FILTER_CLASSES_TOOL_PATH -i $input -m 20 -M 24 -o stdout | intersectBed -a stdin -b $FILTER_CLASSES_MIRNA_REF -s -f 1.0 > $output4 &&

         samtools index $output1 &&
         samtools index $output2 &&
         samtools index $output3 &&
         samtools index $output4

      ""","FilterRNAClasses"
   }
   forward input, output1, output2, output3, output4
}
