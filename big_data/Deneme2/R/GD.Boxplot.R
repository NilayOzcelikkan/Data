postscript("GD.Boxplot.eps", horizontal=FALSE, onefile=FALSE, height=8, width=12, pointsize=10)
resultDirectory<-"../data"
qIndicator <- function(indicator, problem)
{
fileNSGAIIt27<-paste(resultDirectory, "NSGAIIt27", sep="/")
fileNSGAIIt27<-paste(fileNSGAIIt27, problem, sep="/")
fileNSGAIIt27<-paste(fileNSGAIIt27, indicator, sep="/")
NSGAIIt27<-scan(fileNSGAIIt27)

fileSPEA2t27<-paste(resultDirectory, "SPEA2t27", sep="/")
fileSPEA2t27<-paste(fileSPEA2t27, problem, sep="/")
fileSPEA2t27<-paste(fileSPEA2t27, indicator, sep="/")
SPEA2t27<-scan(fileSPEA2t27)

algs<-c("NSGAIIt27","SPEA2t27")
boxplot(NSGAIIt27,SPEA2t27,names=algs, notch = FALSE)
titulo <-paste(indicator, problem, sep=":")
title(main=titulo)
}
par(mfrow=c(3,3))
indicator<-"GD"
qIndicator(indicator, "AppWithSmp")
