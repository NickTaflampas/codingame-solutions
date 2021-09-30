u=input;k=u().split();d={k[3]:k[4]};d.update(u().split() for i in range(int(k[7])))
while 1:a,b,c=u().split();print(("WAIT","BLOCK")[a in d.keys()and(int(b)-int(d.get(a)))*((-1)**len(c))<0])