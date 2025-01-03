#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 6
using namespace std;

int n,m;
int arr[MAX][MAX];
bool visited[MAX][MAX];
int answer;

bool inRange(int i, int j){
    return i>=0 && i<n && j>=0 && j<m;
}

int first(int i, int j){
    if(!inRange(i,j) || !inRange(i,j+1) || !inRange(i+1,j)){
        return -1;
    }
    if(visited[i][j] || visited[i][j+1] || visited[i+1][j]){
        return -1;
    }

    return arr[i][j]*2+arr[i][j+1]+arr[i+1][j];
}

int second(int i, int j){
    if(!inRange(i,j) || !inRange(i,j-1) || !inRange(i+1,j)){
        return -1;
    }
    if(visited[i][j] || visited[i][j-1] || visited[i+1][j]){
        return -1;
    }

    return arr[i][j]*2+arr[i][j-1]+arr[i+1][j];
}

int third(int i, int j){
    if(!inRange(i,j) || !inRange(i,j-1) || !inRange(i-1,j)){
        return -1;
    }
    if(visited[i][j] || visited[i][j-1] || visited[i-1][j]){
        return -1;
    }

    return arr[i][j]*2+arr[i][j-1]+arr[i-1][j];
}

int fourth(int i, int j){
    if(!inRange(i,j) || !inRange(i,j+1) || !inRange(i-1,j)){
        return -1;
    }
    if(visited[i][j] || visited[i][j+1] || visited[i-1][j]){
        return -1;
    }

    return arr[i][j]*2+arr[i][j+1]+arr[i-1][j];
}



void dfs(int i, int j, int sum){
    
    if(j==m){
        i++;
        j=0;
    }

    if(i==n){
        answer=max(answer,sum);
        return;
    }

    if(!visited[i][j]){

        int first_value=first(i,j);
        int second_value=second(i,j);
        int third_value=third(i,j);
        int fourth_value=fourth(i,j);

        if(first_value!=-1){
            visited[i][j]=true;
            visited[i][j+1]=true;
            visited[i+1][j]=true;
            dfs(i,j+1,sum+first_value);
            visited[i][j]=false;
            visited[i][j+1]=false;
            visited[i+1][j]=false;
        }

        if(second_value!=-1){
            visited[i][j]=true;
            visited[i][j-1]=true;
            visited[i+1][j]=true;
            dfs(i,j+1,sum+second_value);
            visited[i][j]=false;
            visited[i][j-1]=false;
            visited[i+1][j]=false;
        }
        if(third_value!=-1){
            visited[i][j]=true;
            visited[i][j-1]=true;
            visited[i-1][j]=true;
            dfs(i,j+1,sum+third_value);
            visited[i][j]=false;
            visited[i][j-1]=false;
            visited[i-1][j]=false;
        }
        if(fourth_value!=-1){
            visited[i][j]=true;
            visited[i][j+1]=true;
            visited[i-1][j]=true;
            dfs(i,j+1,sum+fourth_value);
            visited[i][j]=false;
            visited[i][j+1]=false;
            visited[i-1][j]=false;
        }
    }
    dfs(i,j+1,sum);        
}

int main(){
    cin >> n >> m;

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> arr[i][j];
        }
    }

    if(n==1 || m==1){
        cout << 0 << "\n";
        return 0;
    }

    dfs(0,0,0);
    cout << answer << "\n";

}