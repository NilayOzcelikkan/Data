write("", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex",append=FALSE)
resultDirectory<-"C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/data"
latexHeader <- function() {
  write("\\documentclass{article}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\title{StandardStudy}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\usepackage{amssymb}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\author{A.J.Nebro}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\begin{document}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\maketitle", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\section{Tables}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}

latexTableHeader <- function(problem, tabularString, latexTableFirstLine) {
  write("\\begin{table}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\caption{", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(problem, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(".HV.}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)

  write("\\label{Table:", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(problem, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(".HV.}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)

  write("\\centering", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\begin{scriptsize}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\begin{tabular}{", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(tabularString, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write(latexTableFirstLine, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\hline ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}

printTableLine <- function(indicator, algorithm1, algorithm2, i, j, problem) { 
  file1<-paste(resultDirectory, algorithm1, sep="/")
  file1<-paste(file1, problem, sep="/")
  file1<-paste(file1, indicator, sep="/")
  data1<-scan(file1)
  file2<-paste(resultDirectory, algorithm2, sep="/")
  file2<-paste(file2, problem, sep="/")
  file2<-paste(file2, indicator, sep="/")
  data2<-scan(file2)
  if (i == j) {
    write("--", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  }
  else if (i < j) {
    if (is.finite(wilcox.test(data1, data2)$p.value) & wilcox.test(data1, data2)$p.value <= 0.05) {
      if (median(data1) >= median(data2)) {
        write("$\\blacktriangle$", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}
      else {
        write("$\\triangledown$", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}
}
    else {
      write("$-$", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}
  }
  else {
    write(" ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  }
}

latexTableTail <- function() { 
  write("\\hline", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\end{tabular}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\end{scriptsize}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
  write("\\end{table}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}

latexTail <- function() { 
  write("\\end{document}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
}

### START OF SCRIPT 
# Constants
problemList <-c("AppWithSmp") 
algorithmList <-c("NSGAIIt1", "NSGAIIt2", "NSGAIIt3", "NSGAIIt4", "NSGAIIt5", "NSGAIIt6", "NSGAIIt7", "NSGAIIt8", "NSGAIIt9", "NSGAIIt10", "NSGAIIt11", "NSGAIIt12", "NSGAIIt13", "NSGAIIt14", "NSGAIIt15", "NSGAIIt16", "NSGAIIt17", "NSGAIIt18", "NSGAIIt19", "NSGAIIt20", "NSGAIIt21", "NSGAIIt22", "NSGAIIt23", "NSGAIIt24", "NSGAIIt25", "NSGAIIt26", "NSGAIIt27", "SPEA2t1", "SPEA2t2", "SPEA2t3", "SPEA2t4", "SPEA2t5", "SPEA2t6", "SPEA2t7", "SPEA2t8", "SPEA2t9", "SPEA2t10", "SPEA2t11", "SPEA2t12", "SPEA2t13", "SPEA2t14", "SPEA2t15", "SPEA2t16", "SPEA2t17", "SPEA2t18", "SPEA2t19", "SPEA2t20", "SPEA2t21", "SPEA2t22", "SPEA2t23", "SPEA2t24", "SPEA2t25", "SPEA2t26", "SPEA2t27") 
tabularString <-c("lccccccccccccccccccccccccccccccccccccccccccccccccccccc") 
latexTableFirstLine <-c("\\hline  & NSGAIIt2 & NSGAIIt3 & NSGAIIt4 & NSGAIIt5 & NSGAIIt6 & NSGAIIt7 & NSGAIIt8 & NSGAIIt9 & NSGAIIt10 & NSGAIIt11 & NSGAIIt12 & NSGAIIt13 & NSGAIIt14 & NSGAIIt15 & NSGAIIt16 & NSGAIIt17 & NSGAIIt18 & NSGAIIt19 & NSGAIIt20 & NSGAIIt21 & NSGAIIt22 & NSGAIIt23 & NSGAIIt24 & NSGAIIt25 & NSGAIIt26 & NSGAIIt27 & SPEA2t1 & SPEA2t2 & SPEA2t3 & SPEA2t4 & SPEA2t5 & SPEA2t6 & SPEA2t7 & SPEA2t8 & SPEA2t9 & SPEA2t10 & SPEA2t11 & SPEA2t12 & SPEA2t13 & SPEA2t14 & SPEA2t15 & SPEA2t16 & SPEA2t17 & SPEA2t18 & SPEA2t19 & SPEA2t20 & SPEA2t21 & SPEA2t22 & SPEA2t23 & SPEA2t24 & SPEA2t25 & SPEA2t26 & SPEA2t27\\\\ ") 
indicator<-"HV"

 # Step 1.  Writes the latex header
latexHeader()
tabularString <-c("| l | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | p{0.15cm } | ") 

latexTableFirstLine <-c("\\hline \\multicolumn{1}{|c|}{} & \\multicolumn{1}{c|}{NSGAIIt2} & \\multicolumn{1}{c|}{NSGAIIt3} & \\multicolumn{1}{c|}{NSGAIIt4} & \\multicolumn{1}{c|}{NSGAIIt5} & \\multicolumn{1}{c|}{NSGAIIt6} & \\multicolumn{1}{c|}{NSGAIIt7} & \\multicolumn{1}{c|}{NSGAIIt8} & \\multicolumn{1}{c|}{NSGAIIt9} & \\multicolumn{1}{c|}{NSGAIIt10} & \\multicolumn{1}{c|}{NSGAIIt11} & \\multicolumn{1}{c|}{NSGAIIt12} & \\multicolumn{1}{c|}{NSGAIIt13} & \\multicolumn{1}{c|}{NSGAIIt14} & \\multicolumn{1}{c|}{NSGAIIt15} & \\multicolumn{1}{c|}{NSGAIIt16} & \\multicolumn{1}{c|}{NSGAIIt17} & \\multicolumn{1}{c|}{NSGAIIt18} & \\multicolumn{1}{c|}{NSGAIIt19} & \\multicolumn{1}{c|}{NSGAIIt20} & \\multicolumn{1}{c|}{NSGAIIt21} & \\multicolumn{1}{c|}{NSGAIIt22} & \\multicolumn{1}{c|}{NSGAIIt23} & \\multicolumn{1}{c|}{NSGAIIt24} & \\multicolumn{1}{c|}{NSGAIIt25} & \\multicolumn{1}{c|}{NSGAIIt26} & \\multicolumn{1}{c|}{NSGAIIt27} & \\multicolumn{1}{c|}{SPEA2t1} & \\multicolumn{1}{c|}{SPEA2t2} & \\multicolumn{1}{c|}{SPEA2t3} & \\multicolumn{1}{c|}{SPEA2t4} & \\multicolumn{1}{c|}{SPEA2t5} & \\multicolumn{1}{c|}{SPEA2t6} & \\multicolumn{1}{c|}{SPEA2t7} & \\multicolumn{1}{c|}{SPEA2t8} & \\multicolumn{1}{c|}{SPEA2t9} & \\multicolumn{1}{c|}{SPEA2t10} & \\multicolumn{1}{c|}{SPEA2t11} & \\multicolumn{1}{c|}{SPEA2t12} & \\multicolumn{1}{c|}{SPEA2t13} & \\multicolumn{1}{c|}{SPEA2t14} & \\multicolumn{1}{c|}{SPEA2t15} & \\multicolumn{1}{c|}{SPEA2t16} & \\multicolumn{1}{c|}{SPEA2t17} & \\multicolumn{1}{c|}{SPEA2t18} & \\multicolumn{1}{c|}{SPEA2t19} & \\multicolumn{1}{c|}{SPEA2t20} & \\multicolumn{1}{c|}{SPEA2t21} & \\multicolumn{1}{c|}{SPEA2t22} & \\multicolumn{1}{c|}{SPEA2t23} & \\multicolumn{1}{c|}{SPEA2t24} & \\multicolumn{1}{c|}{SPEA2t25} & \\multicolumn{1}{c|}{SPEA2t26} & \\multicolumn{1}{c|}{SPEA2t27} \\\\") 

# Step 3. Problem loop 
latexTableHeader("AppWithSmp ", tabularString, latexTableFirstLine)

indx = 0
for (i in algorithmList) {
  if (i != "SPEA2t27"){
)    write(i , "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
    write(" & ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)

    jndx = 0
    for (j in algorithmList) {
      for (problem in problemList) {
        if (jndx != 0) {
          if (i != j) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
          } 
          if (problem == "AppWithSmp"){
)            if (j == "SPEA2t27"){
)              write(" \\\\ ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
            } 
            else {
              write(" & ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
            }
          }
     else {
    write("&", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/HV.Wilcoxon.tex", append=TRUE)
     }
        }
      }
      jndx = jndx + 1
}
    indx = indx + 1
  }
} # for algorithm

  latexTableTail()

#Step 3. Writes the end of latex file 
latexTail()

