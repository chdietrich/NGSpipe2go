SplitNCigarReads_vars=[
    outdir          : STAR_pe_2nd_vars.outdir,
    gatk_ref        : ESSENTIAL_GENOME_REF,
    gatk_threads    : ESSENTIAL_THREADS,
    java_flags      : "2400m",
    read_filter_flag: "ReassignOneMappingQuality",
    map_q_from_flag : 255,
    map_q_to_flag   : 60,
    unsafe_flag     : "ALLOW_N_CIGAR_READS"
]
