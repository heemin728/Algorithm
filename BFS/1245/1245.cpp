#include<iostream>
#include<queue>
using namespace std;
#define MAX 101

int n,m;
int map[MAX][MAX];
int dx[8]={1,-1,0,0, 1, 1, -1,-1};
int dy[8]={0,0,1,-1, 1,-1,1,-1};
bool visit[MAX][MAX];
int cnt;

bool isInRange(int i, int j){
    return i>=0 && i<n && j>=0 && j<m;
}

int bfs( ){

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(visit[i][j]){
                continue;
            }
           queue<pair<int,int>> q; 
            vector<pair<int,int>> aa;
            
            int num=map[i][j];
                
            bool possible = true;
            q.push({i,j});
            visit[i][j]=true;
            
            while(!q.empty()){
               
                    int x = q.front().first;
                    int y = q.front().second;
                    q.pop();
                    
                    aa.push_back({x  , y });
                  
                    for(int k=0;k<8;k++){
                        int nx=x+dx[k];
                        int ny=y+dy[k];
                        
                        if(!isInRange(nx,ny)){
                            continue;
                        }
                        int v=map[nx][ny];
                        
                       
                        
                        if(!visit[nx][ny] && v==map[x][y]){               
                            q.push({nx,ny});
                            visit[nx][ny]=true;
                        }
                    }                  
                }
            
            for(int t=0;t<aa.size();t++){
                int f1=aa[t].first;
                int f2=aa[t].second;
                
                for(int k=0;k<8;k++){
                    int f1n=f1+dx[k];
                    int f2n=f2+dy[k];
                    
                    if(!isInRange(f1n,f2n)){
                        continue;
                    }
                    
                    if(map[f1n][f2n] > map[f1][f2]){
                        possible=false;
                        break;
                    }
                }
                }
            
            if(possible){
                cnt++;
            }
        }
    }
    return 0;
}

int main(){
    cin >> n >> m;

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> map[i][j];
        }
    }
    
   bfs();
    cout << cnt << endl;
}
