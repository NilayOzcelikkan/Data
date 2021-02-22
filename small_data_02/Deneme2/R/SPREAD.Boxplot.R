postscript("SPREAD.Boxplot.eps", horizontal=FALSE, onefile=FALSE, height=8, width=12, pointsize=10)
resultDirectory<-"../data"
qIndicator <- function(indicator, problem)
{
fileNSGAIIt1<-paste(resultDirectory, "NSGAIIt1", sep="/")
fileNSGAIIt1<-paste(fileNSGAIIt1, problem, sep="/")
fileNSGAIIt1<-paste(fileNSGAIIt1, indicator, sep="/")
NSGAIIt1<-scan(fileNSGAIIt1)

fileNSGAIIt2<-paste(resultDirectory, "NSGAIIt2", sep="/")
fileNSGAIIt2<-paste(fileNSGAIIt2, problem, sep="/")
fileNSGAIIt2<-paste(fileNSGAIIt2, indicator, sep="/")
NSGAIIt2<-scan(fileNSGAIIt2)

fileNSGAIIt3<-paste(resultDirectory, "NSGAIIt3", sep="/")
fileNSGAIIt3<-paste(fileNSGAIIt3, problem, sep="/")
fileNSGAIIt3<-paste(fileNSGAIIt3, indicator, sep="/")
NSGAIIt3<-scan(fileNSGAIIt3)

fileNSGAIIt4<-paste(resultDirectory, "NSGAIIt4", sep="/")
fileNSGAIIt4<-paste(fileNSGAIIt4, problem, sep="/")
fileNSGAIIt4<-paste(fileNSGAIIt4, indicator, sep="/")
NSGAIIt4<-scan(fileNSGAIIt4)

fileNSGAIIt5<-paste(resultDirectory, "NSGAIIt5", sep="/")
fileNSGAIIt5<-paste(fileNSGAIIt5, problem, sep="/")
fileNSGAIIt5<-paste(fileNSGAIIt5, indicator, sep="/")
NSGAIIt5<-scan(fileNSGAIIt5)

fileNSGAIIt6<-paste(resultDirectory, "NSGAIIt6", sep="/")
fileNSGAIIt6<-paste(fileNSGAIIt6, problem, sep="/")
fileNSGAIIt6<-paste(fileNSGAIIt6, indicator, sep="/")
NSGAIIt6<-scan(fileNSGAIIt6)

fileNSGAIIt7<-paste(resultDirectory, "NSGAIIt7", sep="/")
fileNSGAIIt7<-paste(fileNSGAIIt7, problem, sep="/")
fileNSGAIIt7<-paste(fileNSGAIIt7, indicator, sep="/")
NSGAIIt7<-scan(fileNSGAIIt7)

fileNSGAIIt8<-paste(resultDirectory, "NSGAIIt8", sep="/")
fileNSGAIIt8<-paste(fileNSGAIIt8, problem, sep="/")
fileNSGAIIt8<-paste(fileNSGAIIt8, indicator, sep="/")
NSGAIIt8<-scan(fileNSGAIIt8)

fileNSGAIIt9<-paste(resultDirectory, "NSGAIIt9", sep="/")
fileNSGAIIt9<-paste(fileNSGAIIt9, problem, sep="/")
fileNSGAIIt9<-paste(fileNSGAIIt9, indicator, sep="/")
NSGAIIt9<-scan(fileNSGAIIt9)

fileNSGAIIt10<-paste(resultDirectory, "NSGAIIt10", sep="/")
fileNSGAIIt10<-paste(fileNSGAIIt10, problem, sep="/")
fileNSGAIIt10<-paste(fileNSGAIIt10, indicator, sep="/")
NSGAIIt10<-scan(fileNSGAIIt10)

fileNSGAIIt11<-paste(resultDirectory, "NSGAIIt11", sep="/")
fileNSGAIIt11<-paste(fileNSGAIIt11, problem, sep="/")
fileNSGAIIt11<-paste(fileNSGAIIt11, indicator, sep="/")
NSGAIIt11<-scan(fileNSGAIIt11)

fileNSGAIIt12<-paste(resultDirectory, "NSGAIIt12", sep="/")
fileNSGAIIt12<-paste(fileNSGAIIt12, problem, sep="/")
fileNSGAIIt12<-paste(fileNSGAIIt12, indicator, sep="/")
NSGAIIt12<-scan(fileNSGAIIt12)

fileNSGAIIt13<-paste(resultDirectory, "NSGAIIt13", sep="/")
fileNSGAIIt13<-paste(fileNSGAIIt13, problem, sep="/")
fileNSGAIIt13<-paste(fileNSGAIIt13, indicator, sep="/")
NSGAIIt13<-scan(fileNSGAIIt13)

fileNSGAIIt14<-paste(resultDirectory, "NSGAIIt14", sep="/")
fileNSGAIIt14<-paste(fileNSGAIIt14, problem, sep="/")
fileNSGAIIt14<-paste(fileNSGAIIt14, indicator, sep="/")
NSGAIIt14<-scan(fileNSGAIIt14)

fileNSGAIIt15<-paste(resultDirectory, "NSGAIIt15", sep="/")
fileNSGAIIt15<-paste(fileNSGAIIt15, problem, sep="/")
fileNSGAIIt15<-paste(fileNSGAIIt15, indicator, sep="/")
NSGAIIt15<-scan(fileNSGAIIt15)

fileNSGAIIt16<-paste(resultDirectory, "NSGAIIt16", sep="/")
fileNSGAIIt16<-paste(fileNSGAIIt16, problem, sep="/")
fileNSGAIIt16<-paste(fileNSGAIIt16, indicator, sep="/")
NSGAIIt16<-scan(fileNSGAIIt16)

fileNSGAIIt17<-paste(resultDirectory, "NSGAIIt17", sep="/")
fileNSGAIIt17<-paste(fileNSGAIIt17, problem, sep="/")
fileNSGAIIt17<-paste(fileNSGAIIt17, indicator, sep="/")
NSGAIIt17<-scan(fileNSGAIIt17)

fileNSGAIIt18<-paste(resultDirectory, "NSGAIIt18", sep="/")
fileNSGAIIt18<-paste(fileNSGAIIt18, problem, sep="/")
fileNSGAIIt18<-paste(fileNSGAIIt18, indicator, sep="/")
NSGAIIt18<-scan(fileNSGAIIt18)

fileNSGAIIt19<-paste(resultDirectory, "NSGAIIt19", sep="/")
fileNSGAIIt19<-paste(fileNSGAIIt19, problem, sep="/")
fileNSGAIIt19<-paste(fileNSGAIIt19, indicator, sep="/")
NSGAIIt19<-scan(fileNSGAIIt19)

fileNSGAIIt20<-paste(resultDirectory, "NSGAIIt20", sep="/")
fileNSGAIIt20<-paste(fileNSGAIIt20, problem, sep="/")
fileNSGAIIt20<-paste(fileNSGAIIt20, indicator, sep="/")
NSGAIIt20<-scan(fileNSGAIIt20)

fileNSGAIIt21<-paste(resultDirectory, "NSGAIIt21", sep="/")
fileNSGAIIt21<-paste(fileNSGAIIt21, problem, sep="/")
fileNSGAIIt21<-paste(fileNSGAIIt21, indicator, sep="/")
NSGAIIt21<-scan(fileNSGAIIt21)

fileNSGAIIt22<-paste(resultDirectory, "NSGAIIt22", sep="/")
fileNSGAIIt22<-paste(fileNSGAIIt22, problem, sep="/")
fileNSGAIIt22<-paste(fileNSGAIIt22, indicator, sep="/")
NSGAIIt22<-scan(fileNSGAIIt22)

fileNSGAIIt23<-paste(resultDirectory, "NSGAIIt23", sep="/")
fileNSGAIIt23<-paste(fileNSGAIIt23, problem, sep="/")
fileNSGAIIt23<-paste(fileNSGAIIt23, indicator, sep="/")
NSGAIIt23<-scan(fileNSGAIIt23)

fileNSGAIIt24<-paste(resultDirectory, "NSGAIIt24", sep="/")
fileNSGAIIt24<-paste(fileNSGAIIt24, problem, sep="/")
fileNSGAIIt24<-paste(fileNSGAIIt24, indicator, sep="/")
NSGAIIt24<-scan(fileNSGAIIt24)

fileNSGAIIt25<-paste(resultDirectory, "NSGAIIt25", sep="/")
fileNSGAIIt25<-paste(fileNSGAIIt25, problem, sep="/")
fileNSGAIIt25<-paste(fileNSGAIIt25, indicator, sep="/")
NSGAIIt25<-scan(fileNSGAIIt25)

fileNSGAIIt26<-paste(resultDirectory, "NSGAIIt26", sep="/")
fileNSGAIIt26<-paste(fileNSGAIIt26, problem, sep="/")
fileNSGAIIt26<-paste(fileNSGAIIt26, indicator, sep="/")
NSGAIIt26<-scan(fileNSGAIIt26)

fileNSGAIIt27<-paste(resultDirectory, "NSGAIIt27", sep="/")
fileNSGAIIt27<-paste(fileNSGAIIt27, problem, sep="/")
fileNSGAIIt27<-paste(fileNSGAIIt27, indicator, sep="/")
NSGAIIt27<-scan(fileNSGAIIt27)

fileSPEA2t1<-paste(resultDirectory, "SPEA2t1", sep="/")
fileSPEA2t1<-paste(fileSPEA2t1, problem, sep="/")
fileSPEA2t1<-paste(fileSPEA2t1, indicator, sep="/")
SPEA2t1<-scan(fileSPEA2t1)

fileSPEA2t2<-paste(resultDirectory, "SPEA2t2", sep="/")
fileSPEA2t2<-paste(fileSPEA2t2, problem, sep="/")
fileSPEA2t2<-paste(fileSPEA2t2, indicator, sep="/")
SPEA2t2<-scan(fileSPEA2t2)

fileSPEA2t3<-paste(resultDirectory, "SPEA2t3", sep="/")
fileSPEA2t3<-paste(fileSPEA2t3, problem, sep="/")
fileSPEA2t3<-paste(fileSPEA2t3, indicator, sep="/")
SPEA2t3<-scan(fileSPEA2t3)

fileSPEA2t4<-paste(resultDirectory, "SPEA2t4", sep="/")
fileSPEA2t4<-paste(fileSPEA2t4, problem, sep="/")
fileSPEA2t4<-paste(fileSPEA2t4, indicator, sep="/")
SPEA2t4<-scan(fileSPEA2t4)

fileSPEA2t5<-paste(resultDirectory, "SPEA2t5", sep="/")
fileSPEA2t5<-paste(fileSPEA2t5, problem, sep="/")
fileSPEA2t5<-paste(fileSPEA2t5, indicator, sep="/")
SPEA2t5<-scan(fileSPEA2t5)

fileSPEA2t6<-paste(resultDirectory, "SPEA2t6", sep="/")
fileSPEA2t6<-paste(fileSPEA2t6, problem, sep="/")
fileSPEA2t6<-paste(fileSPEA2t6, indicator, sep="/")
SPEA2t6<-scan(fileSPEA2t6)

fileSPEA2t7<-paste(resultDirectory, "SPEA2t7", sep="/")
fileSPEA2t7<-paste(fileSPEA2t7, problem, sep="/")
fileSPEA2t7<-paste(fileSPEA2t7, indicator, sep="/")
SPEA2t7<-scan(fileSPEA2t7)

fileSPEA2t8<-paste(resultDirectory, "SPEA2t8", sep="/")
fileSPEA2t8<-paste(fileSPEA2t8, problem, sep="/")
fileSPEA2t8<-paste(fileSPEA2t8, indicator, sep="/")
SPEA2t8<-scan(fileSPEA2t8)

fileSPEA2t9<-paste(resultDirectory, "SPEA2t9", sep="/")
fileSPEA2t9<-paste(fileSPEA2t9, problem, sep="/")
fileSPEA2t9<-paste(fileSPEA2t9, indicator, sep="/")
SPEA2t9<-scan(fileSPEA2t9)

fileSPEA2t10<-paste(resultDirectory, "SPEA2t10", sep="/")
fileSPEA2t10<-paste(fileSPEA2t10, problem, sep="/")
fileSPEA2t10<-paste(fileSPEA2t10, indicator, sep="/")
SPEA2t10<-scan(fileSPEA2t10)

fileSPEA2t11<-paste(resultDirectory, "SPEA2t11", sep="/")
fileSPEA2t11<-paste(fileSPEA2t11, problem, sep="/")
fileSPEA2t11<-paste(fileSPEA2t11, indicator, sep="/")
SPEA2t11<-scan(fileSPEA2t11)

fileSPEA2t12<-paste(resultDirectory, "SPEA2t12", sep="/")
fileSPEA2t12<-paste(fileSPEA2t12, problem, sep="/")
fileSPEA2t12<-paste(fileSPEA2t12, indicator, sep="/")
SPEA2t12<-scan(fileSPEA2t12)

fileSPEA2t13<-paste(resultDirectory, "SPEA2t13", sep="/")
fileSPEA2t13<-paste(fileSPEA2t13, problem, sep="/")
fileSPEA2t13<-paste(fileSPEA2t13, indicator, sep="/")
SPEA2t13<-scan(fileSPEA2t13)

fileSPEA2t14<-paste(resultDirectory, "SPEA2t14", sep="/")
fileSPEA2t14<-paste(fileSPEA2t14, problem, sep="/")
fileSPEA2t14<-paste(fileSPEA2t14, indicator, sep="/")
SPEA2t14<-scan(fileSPEA2t14)

fileSPEA2t15<-paste(resultDirectory, "SPEA2t15", sep="/")
fileSPEA2t15<-paste(fileSPEA2t15, problem, sep="/")
fileSPEA2t15<-paste(fileSPEA2t15, indicator, sep="/")
SPEA2t15<-scan(fileSPEA2t15)

fileSPEA2t16<-paste(resultDirectory, "SPEA2t16", sep="/")
fileSPEA2t16<-paste(fileSPEA2t16, problem, sep="/")
fileSPEA2t16<-paste(fileSPEA2t16, indicator, sep="/")
SPEA2t16<-scan(fileSPEA2t16)

fileSPEA2t17<-paste(resultDirectory, "SPEA2t17", sep="/")
fileSPEA2t17<-paste(fileSPEA2t17, problem, sep="/")
fileSPEA2t17<-paste(fileSPEA2t17, indicator, sep="/")
SPEA2t17<-scan(fileSPEA2t17)

fileSPEA2t18<-paste(resultDirectory, "SPEA2t18", sep="/")
fileSPEA2t18<-paste(fileSPEA2t18, problem, sep="/")
fileSPEA2t18<-paste(fileSPEA2t18, indicator, sep="/")
SPEA2t18<-scan(fileSPEA2t18)

fileSPEA2t19<-paste(resultDirectory, "SPEA2t19", sep="/")
fileSPEA2t19<-paste(fileSPEA2t19, problem, sep="/")
fileSPEA2t19<-paste(fileSPEA2t19, indicator, sep="/")
SPEA2t19<-scan(fileSPEA2t19)

fileSPEA2t20<-paste(resultDirectory, "SPEA2t20", sep="/")
fileSPEA2t20<-paste(fileSPEA2t20, problem, sep="/")
fileSPEA2t20<-paste(fileSPEA2t20, indicator, sep="/")
SPEA2t20<-scan(fileSPEA2t20)

fileSPEA2t21<-paste(resultDirectory, "SPEA2t21", sep="/")
fileSPEA2t21<-paste(fileSPEA2t21, problem, sep="/")
fileSPEA2t21<-paste(fileSPEA2t21, indicator, sep="/")
SPEA2t21<-scan(fileSPEA2t21)

fileSPEA2t22<-paste(resultDirectory, "SPEA2t22", sep="/")
fileSPEA2t22<-paste(fileSPEA2t22, problem, sep="/")
fileSPEA2t22<-paste(fileSPEA2t22, indicator, sep="/")
SPEA2t22<-scan(fileSPEA2t22)

fileSPEA2t23<-paste(resultDirectory, "SPEA2t23", sep="/")
fileSPEA2t23<-paste(fileSPEA2t23, problem, sep="/")
fileSPEA2t23<-paste(fileSPEA2t23, indicator, sep="/")
SPEA2t23<-scan(fileSPEA2t23)

fileSPEA2t24<-paste(resultDirectory, "SPEA2t24", sep="/")
fileSPEA2t24<-paste(fileSPEA2t24, problem, sep="/")
fileSPEA2t24<-paste(fileSPEA2t24, indicator, sep="/")
SPEA2t24<-scan(fileSPEA2t24)

fileSPEA2t25<-paste(resultDirectory, "SPEA2t25", sep="/")
fileSPEA2t25<-paste(fileSPEA2t25, problem, sep="/")
fileSPEA2t25<-paste(fileSPEA2t25, indicator, sep="/")
SPEA2t25<-scan(fileSPEA2t25)

fileSPEA2t26<-paste(resultDirectory, "SPEA2t26", sep="/")
fileSPEA2t26<-paste(fileSPEA2t26, problem, sep="/")
fileSPEA2t26<-paste(fileSPEA2t26, indicator, sep="/")
SPEA2t26<-scan(fileSPEA2t26)

fileSPEA2t27<-paste(resultDirectory, "SPEA2t27", sep="/")
fileSPEA2t27<-paste(fileSPEA2t27, problem, sep="/")
fileSPEA2t27<-paste(fileSPEA2t27, indicator, sep="/")
SPEA2t27<-scan(fileSPEA2t27)

algs<-c("NSGAIIt1","NSGAIIt2","NSGAIIt3","NSGAIIt4","NSGAIIt5","NSGAIIt6","NSGAIIt7","NSGAIIt8","NSGAIIt9","NSGAIIt10","NSGAIIt11","NSGAIIt12","NSGAIIt13","NSGAIIt14","NSGAIIt15","NSGAIIt16","NSGAIIt17","NSGAIIt18","NSGAIIt19","NSGAIIt20","NSGAIIt21","NSGAIIt22","NSGAIIt23","NSGAIIt24","NSGAIIt25","NSGAIIt26","NSGAIIt27","SPEA2t1","SPEA2t2","SPEA2t3","SPEA2t4","SPEA2t5","SPEA2t6","SPEA2t7","SPEA2t8","SPEA2t9","SPEA2t10","SPEA2t11","SPEA2t12","SPEA2t13","SPEA2t14","SPEA2t15","SPEA2t16","SPEA2t17","SPEA2t18","SPEA2t19","SPEA2t20","SPEA2t21","SPEA2t22","SPEA2t23","SPEA2t24","SPEA2t25","SPEA2t26","SPEA2t27")
boxplot(NSGAIIt1,NSGAIIt2,NSGAIIt3,NSGAIIt4,NSGAIIt5,NSGAIIt6,NSGAIIt7,NSGAIIt8,NSGAIIt9,NSGAIIt10,NSGAIIt11,NSGAIIt12,NSGAIIt13,NSGAIIt14,NSGAIIt15,NSGAIIt16,NSGAIIt17,NSGAIIt18,NSGAIIt19,NSGAIIt20,NSGAIIt21,NSGAIIt22,NSGAIIt23,NSGAIIt24,NSGAIIt25,NSGAIIt26,NSGAIIt27,SPEA2t1,SPEA2t2,SPEA2t3,SPEA2t4,SPEA2t5,SPEA2t6,SPEA2t7,SPEA2t8,SPEA2t9,SPEA2t10,SPEA2t11,SPEA2t12,SPEA2t13,SPEA2t14,SPEA2t15,SPEA2t16,SPEA2t17,SPEA2t18,SPEA2t19,SPEA2t20,SPEA2t21,SPEA2t22,SPEA2t23,SPEA2t24,SPEA2t25,SPEA2t26,SPEA2t27,names=algs, notch = FALSE)
titulo <-paste(indicator, problem, sep=":")
title(main=titulo)
}
par(mfrow=c(3,3))
indicator<-"SPREAD"
qIndicator(indicator, "AppWithSmp")
