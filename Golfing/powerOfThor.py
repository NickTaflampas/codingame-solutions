k,l,x,y=[int(i)for i in input().split()]
while 1:m,y=[[['',y],['N',y-1]][y>l],['S',y+1]][y<l];m,x=[[[m+'',x],[m+'W',x-1]][x>k],[m+'E',x+1]][x<k];print(m)