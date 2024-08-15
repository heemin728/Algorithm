#include<iostream>
using namespace std;
#define MAX 300001

int N;
int parent[MAX];

void initiate(){
    for(int i=1;i<=N;i++){
        parent[i]=i;
    }
}

int find(int x){
    if(x==parent[x]) return x;
    return parent[x]=find(parent[x]);
}

void unionParent(int x, int y){
    int pX=find(x);
    int pY=find(y);

    if(pX<pY) parent[pY]=pX;
    else parent[pX]=pY;
}

int main(){
    cin >> N;
    int a,b;

    initiate();
    for(int i=0;i<N-2;i++){
        cin >> a >> b;
        unionParent(a,b);
    }

    // for(int i=1;i<=N;i++){
    //     cout << parent[i] << " ";
    // }
    // cout << "\n";

    cout << 1 << " ";
    for(int i=1;i<=N;i++){
        if(find(i)!=parent[1]){
            cout << i << "\n";
            break;
        }
    }
    
}