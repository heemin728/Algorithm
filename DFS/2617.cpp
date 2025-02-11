#include <stdio.h>
#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
#include <stack>

using namespace std;

#define MAX 101
int n, N;
bool visit[2][MAX];
vector<int> light[MAX];
vector<int> heavy[MAX];
bool check[MAX];
int cnt = 0;

int dfs(int start, vector<int> graph[MAX], int row){
    int dfs_cnt = 1; // 현재 순위
    for(int i = 0; i < (int)graph[start].size(); i++){
        if(visit[row][graph[start][i]] == false){
            visit[row][graph[start][i]] = true; 
            dfs_cnt += dfs(graph[start][i], graph, row);
        }
    }
    return dfs_cnt;
}


int main(){
    int n, N;
    cin >> n >> N;

    for(int i = 0; i< N; i++){
        int m1, m2; //m1 > m2
        cin >> m1 >> m2;
        light[m1].push_back(m2);
        heavy[m2].push_back(m1);
    }

    for (int i = 1; i <= n; i++) {
        memset(visit, false, sizeof(visit));
        visit[0][i] = visit[1][i] = true;

        int h_rank = dfs(i, heavy, 0);
        int l_dfs_cnt = dfs(i, light, 1);

        if (h_rank > (n + 1) / 2 || l_dfs_cnt > (n + 1) / 2){ 
            check[i] = true;
        }
    }

    for (int i = 0; i <= n; i++){
        if (check[i] == true){
            cnt++; //
        }
    }

    cout << cnt << endl;
        return 0;
}
