#base no business   centos:v1(glibc>=2.15)+jdk
#FROM 192.168.80.41:5000/openjdk1.8:v1.0_rc
FROM 192.168.80.41:5001/centos:v1
RUN yum install -y java


#base 1.0
FROM 192.168.80.41:5001/audio-analysis:v1
RUN mkdir -p /home/idcmon/audioAnalysis
ADD audioAnalysis/audioAnalysis-0.0.1-SNAPSHOT.jar /home/idcmon/audioAnalysis/
COPY audioAnalysis/bin /home/idcmon/audioAnalysis/bin
COPY audioAnalysis/lib /home/idcmon/audioAnalysis/lib
WORKDIR /home/idcmon/audioAnalysis/bin