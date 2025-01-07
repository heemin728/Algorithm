#include <iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 61
using namespace std;
int N;
vector<int> v;

struct scv{
    int a;
    int b;
    int c;
};

int visited[MAX][MAX][MAX];
int attack[6][3]={
    {9,3,1},
    {9,1,3},
    {1,3,9},
    {1,9,3},
    {3,9,1},
    {3,1,9}
};

int main()
{
    cin >> N;
    
    for(int i=0;i<N;i++){
        int num;
        cin >> num;
        v.push_back(num);
    }

    if(N==1){
        v.push_back(0);
        v.push_back(0);
    }
    if(N==2){
        v.push_back(0);
    }
    
    visited[v[0]][v[1]][v[2]]=1;
    
    queue<scv> q;
    q.push({v[0],v[1],v[2]});
    int t=0;
    while(!q.empty()){
        int a=q.front().a;
        int b=q.front().b;
        int c=q.front().c;
        int d=visited[a][b][c];
        //cout << a <<" " << b <<" " << c << "\n";
        q.pop();
        
        for(int k=0;k<6;k++){
            int na=a-attack[k][0];
            int nb=b-attack[k][1];
            int nc=c-attack[k][2];
            
            if(na<0){
                na=0;
            }
            if(nb<0){
                nb=0;
            }
            if(nc<0){
                nc=0;
            }

            if(visited[na][nb][nc]>0){
                continue;
            }
            
            if(na==0 && nb==0 && nc==0){
                cout << d << "\n";
                return 0;
            }
            
            visited[na][nb][nc]=d+1;
            q.push({na,nb,nc});
        }
    }
    
    return 0;
}
